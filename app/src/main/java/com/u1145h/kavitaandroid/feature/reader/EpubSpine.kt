package com.u1145h.kavitaandroid.feature.reader

import android.content.Context
import android.util.Xml
import com.u1145h.kavitaandroid.core.util.shortHash
import org.xmlpull.v1.XmlPullParser
import java.io.File
import java.util.zip.ZipFile

/**
 * An EPUB unpacked into a temp directory, with the spine (reading order)
 * resolved from the OPF manifest.
 */
class EpubSpine internal constructor(
    val dir: File,
    val title: String,
    /** Absolute file paths of the spine documents, in reading order. */
    val spine: List<File>,
    /** The spine hrefs, used as stable progress identifiers (bookScrollId). */
    val hrefs: List<String>,
) : AutoCloseable {
    val pageCount: Int get() = spine.size

    fun hrefIndex(href: String): Int = hrefs.indexOf(href).coerceAtLeast(0)

    override fun close() {
        runCatching { dir.deleteRecursively() }
    }
}

/** Unpacks an EPUB and parses its spine. Returns null on malformed files. */
object EpubParser {

    fun extract(context: Context, path: String): EpubSpine? {
        val dir = File(context.cacheDir, "epub-${path.shortHash()}").apply {
            deleteRecursively()
            mkdirs()
        }
        return runCatching {
            unzip(File(path), dir)
            val containerFile = File(dir, "META-INF/container.xml")
            val rootfilePath = parseContainer(containerFile) ?: return null
            val opfFile = File(dir, rootfilePath)
            val (title, manifest, spineIdrefs) = parseOpf(opfFile) ?: return null
            val baseDir = opfFile.parentFile
            val spine = spineIdrefs.mapNotNull { id ->
                val href = manifest[id] ?: return@mapNotNull null
                File(baseDir, href).takeIf { it.exists() }
            }
            val hrefs = spineIdrefs.mapNotNull { manifest[it] }
            EpubSpine(dir, title, spine, hrefs)
        }.getOrNull()?.also { spine ->
            if (spine.spine.isEmpty()) {
                spine.close()
                return null
            }
        }
    }

    private fun unzip(file: File, dir: File) {
        ZipFile(file).use { zip ->
            zip.entries().asSequence().forEach { entry ->
                val dest = File(dir, entry.name)
                if (entry.isDirectory) {
                    dest.mkdirs()
                } else {
                    dest.parentFile?.mkdirs()
                    zip.getInputStream(entry).use { input ->
                        dest.outputStream().use { output -> input.copyTo(output) }
                    }
                }
            }
        }
    }

    private fun parseContainer(file: File): String? {
        if (!file.exists()) return null
        val parser = Xml.newPullParser()
        parser.setInput(file.inputStream(), "UTF-8")
        var rootfile: String? = null
        var event = parser.eventType
        while (event != XmlPullParser.END_DOCUMENT) {
            if (event == XmlPullParser.START_TAG && parser.name == "rootfile") {
                rootfile = parser.getAttributeValue(null, "full-path")
            }
            event = parser.next()
        }
        return rootfile
    }

    private fun parseOpf(
        file: File,
    ): Triple<String, Map<String, String>, List<String>>? {
        if (!file.exists()) return null
        val manifest = HashMap<String, String>()
        val spineIdrefs = ArrayList<String>()
        var title = ""
        var inMetadata = false
        val parser = Xml.newPullParser()
        parser.setInput(file.inputStream(), "UTF-8")
        var event = parser.eventType
        while (event != XmlPullParser.END_DOCUMENT) {
            when (event) {
                XmlPullParser.START_TAG -> when (parser.name) {
                    "metadata" -> inMetadata = true
                    "title" -> if (inMetadata && title.isEmpty()) title = parser.nextText().trim()
                    "item" -> {
                        val id = parser.getAttributeValue(null, "id") ?: ""
                        val href = parser.getAttributeValue(null, "href") ?: ""
                        if (id.isNotEmpty() && href.isNotEmpty()) manifest[id] = href
                    }
                    "itemref" -> {
                        val idref = parser.getAttributeValue(null, "idref") ?: ""
                        if (idref.isNotEmpty()) spineIdrefs.add(idref)
                    }
                }

                XmlPullParser.END_TAG -> if (parser.name == "metadata") inMetadata = false
            }
            event = parser.next()
        }
        return Triple(title, manifest, spineIdrefs)
    }
}
