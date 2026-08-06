package com.u1145h.kavitaandroid.feature.reader

import android.content.Context
import com.github.junrar.Archive
import com.github.junrar.rarfile.FileHeader
import com.u1145h.kavitaandroid.core.util.shortHash
import com.u1145h.kavitaandroid.domain.model.BookFormat
import java.io.File
import java.io.FileOutputStream
import java.util.zip.ZipFile

/** A comic archive (CBZ/CBR) extracted to a temp directory of image pages. */
class ComicArchive internal constructor(
    val dir: File,
    val files: List<File>,
) : AutoCloseable {
    val pageCount: Int get() = files.size

    override fun close() {
        runCatching { dir.deleteRecursively() }
    }
}

/** Extracts CBZ (zip) and CBR (rar) comics into a sorted list of page images. */
object ComicExtractor {

    private val IMAGE_EXTENSIONS = setOf("jpg", "jpeg", "png", "gif", "webp", "bmp")

    fun extract(context: Context, path: String): ComicArchive {
        val dir = File(context.cacheDir, "comic-${path.shortHash()}").apply {
            deleteRecursively()
            mkdirs()
        }
        val format = BookFormat.fromFileName(path)
        val files = when (format) {
            BookFormat.CBZ -> extractZip(File(path), dir)
            BookFormat.CBR -> extractRar(File(path), dir)
            else -> emptyList()
        }
        return ComicArchive(dir, files)
    }

    private fun extractZip(file: File, dir: File): List<File> {
        val entries = ZipFile(file).use { zip ->
            zip.entries().asSequence()
                .filter { !it.isDirectory && isImage(it.name) }
                .sortedWith(Comparator { a, b -> naturalCompare(a.name, b.name) })
                .toList()
        }
        val out = ArrayList<File>(entries.size)
        ZipFile(file).use { zip ->
            entries.forEachIndexed { index, entry ->
                val dest = File(dir, index.toString().padStart(4, '0') + ".img")
                zip.getInputStream(entry).use { input ->
                    dest.outputStream().use { output -> input.copyTo(output) }
                }
                out.add(dest)
            }
        }
        return out
    }

    private fun extractRar(file: File, dir: File): List<File> {
        val headers = Archive(file).use { archive ->
            archive.fileHeaders.filter { !it.isDirectory && isImage(it.fileName) }
                .sortedWith(Comparator { a, b -> naturalCompare(a.fileName, b.fileName) })
        }
        val out = ArrayList<File>(headers.size)
        Archive(file).use { archive ->
            headers.forEachIndexed { index, header ->
                val dest = File(dir, index.toString().padStart(4, '0') + ".img")
                archive.extractFile(header, FileOutputStream(dest))
                out.add(dest)
            }
        }
        return out
    }

    private fun isImage(name: String): Boolean {
        val ext = name.substringAfterLast('.', "").lowercase()
        return ext in IMAGE_EXTENSIONS
    }

    private fun naturalCompare(a: String, b: String): Int {
        var i = 0
        var j = 0
        while (i < a.length && j < b.length) {
            if (a[i].isDigit() && b[j].isDigit()) {
                var x = i
                var y = j
                while (x < a.length && a[x].isDigit()) x++
                while (y < b.length && b[y].isDigit()) y++
                val numA = a.substring(i, x).toLong()
                val numB = b.substring(j, y).toLong()
                if (numA != numB) return numA.compareTo(numB)
                i = x
                j = y
            } else {
                val cmp = a[i].compareTo(b[j])
                if (cmp != 0) return cmp
                i++
                j++
            }
        }
        return a.length - b.length
    }
}
