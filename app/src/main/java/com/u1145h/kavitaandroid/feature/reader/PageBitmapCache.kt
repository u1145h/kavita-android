package com.u1145h.kavitaandroid.feature.reader

import android.graphics.Bitmap
import android.util.LruCache

/**
 * Small in-memory cache of decoded reader pages keyed by page index, bounded
 * by total byte size to avoid OOM on large PDF/comic pages.
 */
class PageBitmapCache(
    private val maxBytes: Int = 48 * 1024 * 1024,
) {
    private val cache = object : LruCache<Int, Bitmap>(maxBytes) {
        override fun sizeOf(key: Int, value: Bitmap): Int = value.byteCount.coerceAtLeast(1)
    }

    operator fun get(index: Int): Bitmap? = cache.get(index)

    operator fun set(index: Int, bitmap: Bitmap) {
        cache.put(index, bitmap)
    }

    fun clear() = cache.evictAll()
}
