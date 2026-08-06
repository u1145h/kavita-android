package com.u1145h.kavitaandroid.feature.reader

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.ParcelFileDescriptor
import java.io.File

/** Lazily renders pages of a PDF file through [PdfRenderer]. */
class PdfPages(path: String) {

    private val descriptor = ParcelFileDescriptor.open(File(path), ParcelFileDescriptor.MODE_READ_ONLY)
    private val renderer = PdfRenderer(descriptor)

    val pageCount: Int get() = renderer.pageCount

    fun render(index: Int, targetWidth: Int, targetHeight: Int): Bitmap {
        val page = renderer.openPage(index)
        return try {
            val scaleX = targetWidth.toFloat() / page.width
            val scaleY = targetHeight.toFloat() / page.height
            val scale = scaleX.coerceAtMost(scaleY)
            val width = (page.width * scale).toInt().coerceAtLeast(1)
            val height = (page.height * scale).toInt().coerceAtLeast(1)
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
            page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
            bitmap
        } finally {
            page.close()
        }
    }

    fun close() {
        runCatching { renderer.close() }
        runCatching { descriptor.close() }
    }
}
