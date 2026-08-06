package com.u1145h.kavitaandroid.work

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import com.u1145h.kavitaandroid.core.config.ServerConfig
import com.u1145h.kavitaandroid.data.local.db.dao.DownloadQueueDao
import com.u1145h.kavitaandroid.data.local.files.BookFileManager
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager
import com.u1145h.kavitaandroid.data.repository.BookRepository
import com.u1145h.kavitaandroid.data.repository.KavitaRepository
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import com.u1145h.kavitaandroid.domain.model.Book
import com.u1145h.kavitaandroid.domain.model.BookFormat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Moves a completed DownloadManager file into the offline library and records
 * its metadata (title, author, series, cover, format) in Room.
 */
@Singleton
class DownloadImporter @Inject constructor(
    @ApplicationContext private val context: Context,
    private val downloadQueueDao: DownloadQueueDao,
    private val bookRepository: BookRepository,
    private val kavitaRepository: KavitaRepository,
    private val fileManager: BookFileManager,
    private val settingsRepository: SettingsRepository,
    private val sessionManager: SessionManager,
) {

    suspend fun importCompletedDownload(downloadId: Long): Result<Unit> = runCatching {
        val downloadManager =
            context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val queueItem = downloadQueueDao.get(downloadId) ?: return Result.success(Unit)

        val cursor = downloadManager.query(DownloadManager.Query().setFilterById(downloadId))
        cursor.use { c ->
            if (!c.moveToFirst()) return Result.success(Unit)
            val status = c.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS)
            if (c.getInt(status) != DownloadManager.STATUS_SUCCESSFUL) return Result.success(Unit)
            val localUri = c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI))
            val mimeType = runCatching {
                c.getString(c.getColumnIndexOrThrow(DownloadManager.COLUMN_MEDIA_TYPE))
            }.getOrNull() ?: ""

            val sourceName = queueItem.filename ?: Uri.parse(localUri).lastPathSegment ?: "book"
            val format = BookFormat.fromFileName(sourceName)
                ?: mimeToFormat(mimeType)
                ?: return Result.failure(IllegalStateException("Unsupported download format: $sourceName"))

            val destination = fileManager.bookFile(format, sourceName)
            val uri = Uri.parse(localUri)
            val input = context.contentResolver.openInputStream(uri)
                ?: return Result.failure(IllegalStateException("Cannot read downloaded file"))
            input.use { it.copyTo(destination.outputStream()) }
            downloadQueueDao.remove(downloadId)

            val book = buildBook(
                queueItemChapterId = queueItem.chapterId,
                queueItemSeriesId = queueItem.seriesId,
                queueItemVolumeId = queueItem.volumeId,
                queueItemLibraryId = queueItem.libraryId,
                fallbackTitle = queueItem.title,
                format = format,
                filePath = destination.absolutePath,
            )
            bookRepository.saveBook(book)
            Unit
        }
    }

    private suspend fun buildBook(
        queueItemChapterId: Int?,
        queueItemSeriesId: Int?,
        queueItemVolumeId: Int?,
        queueItemLibraryId: Int?,
        fallbackTitle: String,
        format: BookFormat,
        filePath: String,
    ): Book {
        var title = fallbackTitle
        var series = ""
        var pages = 0
        var chapterId = queueItemChapterId
        var seriesId = queueItemSeriesId
        var volumeId = queueItemVolumeId
        var libraryId = queueItemLibraryId

        if (chapterId != null) {
            val info = kavitaRepository.getBookInfo(chapterId).getOrNull()
            val chapterInfo = kavitaRepository.getChapterInfo(chapterId).getOrNull()
            title = info?.bookTitle
                ?: chapterInfo?.chapterTitle
                ?: chapterInfo?.title
                ?: title
            series = info?.seriesName ?: chapterInfo?.seriesName ?: series
            pages = if ((info?.pages ?: 0) > 0) (info?.pages ?: 0) else (chapterInfo?.pages ?: 0)
            seriesId = seriesId ?: info?.seriesId.takeIf { it != 0 }
            volumeId = volumeId ?: info?.volumeId.takeIf { it != 0 }
            libraryId = libraryId ?: info?.libraryId.takeIf { it != 0 }
        }

        var coverPath: String? = null
        var coverUrl: String? = null
        if (seriesId != null) {
            val coverFile = fileManager.coverFile(seriesId)
            if (!coverFile.exists()) {
                kavitaRepository.fetchSeriesCover(seriesId, coverFile).getOrNull()
            }
            if (coverFile.exists()) coverPath = coverFile.absolutePath
            val serverUrl = settingsRepository.serverUrl.value.trimEnd('/')
            val apiKey = sessionManager.apiKey
            coverUrl = serverUrl + "/api/Image/series-cover?seriesId=$seriesId" +
                (apiKey?.let { "&apiKey=$it" } ?: "")
        }

        return Book(
            chapterId = chapterId,
            seriesId = seriesId,
            volumeId = volumeId,
            libraryId = libraryId,
            title = title,
            author = "",
            series = series,
            format = format,
            filePath = filePath,
            coverPath = coverPath,
            coverUrl = coverUrl,
            fileSizeBytes = runCatching { java.io.File(filePath).length() }.getOrDefault(0L),
            totalPages = pages,
        )
    }

    private fun mimeToFormat(mimeType: String): BookFormat? = when (mimeType.lowercase()) {
        "application/epub+zip" -> BookFormat.EPUB
        "application/pdf" -> BookFormat.PDF
        "application/vnd.comicbook+zip" -> BookFormat.CBZ
        "application/x-cbz" -> BookFormat.CBZ
        "application/vnd.comicbook-rar" -> BookFormat.CBR
        "application/x-cbr" -> BookFormat.CBR
        else -> null
    }
}
