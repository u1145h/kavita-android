package com.u1145h.kavitaandroid.data.remote

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import com.u1145h.kavitaandroid.data.local.db.dao.DownloadQueueDao
import com.u1145h.kavitaandroid.data.local.db.entity.DownloadQueueEntity
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

/**
 * Enqueues book downloads through Android's [DownloadManager] and records them
 * in the download queue so [DownloadCompleteReceiver] can import them into the
 * offline library.
 *
 * The embedded WebView cannot hand us the binary stream, so we enqueue the URL
 * again through DownloadManager. Because DownloadManager does not share the
 * WebView cookie jar, authenticated requests attach the session token as a
 * `Authorization` header.
 */
@Singleton
class DownloadCoordinator @Inject constructor(
    @ApplicationContext private val context: Context,
    private val downloadManager: DownloadManager,
    private val sessionManager: SessionManager,
    private val downloadQueueDao: DownloadQueueDao,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    /** Enqueues [url] and returns the DownloadManager id. */
    fun enqueue(url: String, suggestedName: String = "", mimeType: String = ""): Long {
        val uri = Uri.parse(url)
        val request = DownloadManager.Request(uri)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(false)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setMimeType(mimeType.ifBlank { "application/octet-stream" })
        sessionManager.token?.takeIf { it.isNotBlank() }?.let {
            request.addRequestHeader("Authorization", "Bearer $it")
        }
        val downloadId = downloadManager.enqueue(request)

        val title = suggestedName.ifBlank {
            uri.lastPathSegment?.substringBeforeLast('/')?.ifBlank { null }
                ?: "Book"
        }
        scope.launch {
            downloadQueueDao.upsert(
                DownloadQueueEntity(
                    downloadId = downloadId,
                    chapterId = queryInt(uri, "chapterId"),
                    volumeId = queryInt(uri, "volumeId"),
                    seriesId = queryInt(uri, "seriesId"),
                    libraryId = queryInt(uri, "libraryId"),
                    title = title,
                    filename = suggestedName.ifBlank { null },
                ),
            )
        }
        return downloadId
    }

    private fun queryInt(uri: Uri, key: String): Int? = uri.getQueryParameter(key)?.toIntOrNull()
}
