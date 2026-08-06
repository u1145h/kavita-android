package com.u1145h.kavitaandroid.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.u1145h.kavitaandroid.core.config.ServerConfig
import com.u1145h.kavitaandroid.core.util.parseIsoToMillis
import com.u1145h.kavitaandroid.data.local.files.BookFileManager
import com.u1145h.kavitaandroid.data.remote.dto.ProgressDto
import com.u1145h.kavitaandroid.data.repository.BookRepository
import com.u1145h.kavitaandroid.data.repository.KavitaRepository
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.util.concurrent.TimeUnit

/**
 * Periodic background synchronization:
 *  - pushes dirty reading progress to the server (conflicts resolved by
 *    keeping the newer `lastModifiedUtc`),
 *  - downloads missing cover artwork,
 *  - records the last sync timestamp.
 */
@HiltWorker
class SyncWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val bookRepository: BookRepository,
    private val kavitaRepository: KavitaRepository,
    private val fileManager: BookFileManager,
    private val settingsRepository: SettingsRepository,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        pushDirtyProgress()
        fetchMissingCovers()
        settingsRepository.setLastSyncAt(System.currentTimeMillis())
        return Result.success()
    }

    private suspend fun pushDirtyProgress() {
        for (book in bookRepository.getDirtyBooks()) {
            val chapterId = book.chapterId ?: continue
            val serverModified = kavitaRepository.getProgress(chapterId)
                .getOrNull()
                ?.lastModifiedUtc
                ?.let { parseIsoToMillis(it) }
                ?: 0L

            if (serverModified > book.lastModifiedUtc) {
                // Server progress is newer; don't overwrite it.
                bookRepository.markSynced(book.id, serverModified)
                continue
            }

            val dto = ProgressDto(
                chapterId = chapterId,
                pageNum = book.currentPage,
                seriesId = book.seriesId ?: 0,
                volumeId = book.volumeId ?: 0,
                libraryId = book.libraryId ?: 0,
                bookScrollId = book.bookScrollId,
            )
            if (kavitaRepository.saveProgress(dto).isSuccess) {
                bookRepository.markSynced(book.id, book.lastModifiedUtc)
            }
        }
    }

    private suspend fun fetchMissingCovers() {
        for (book in bookRepository.getMissingCovers()) {
            val seriesId = book.seriesId ?: continue
            val coverFile = fileManager.coverFile(seriesId)
            val fetched = kavitaRepository.fetchSeriesCover(seriesId, coverFile).isSuccess
            if (fetched && coverFile.exists()) {
                bookRepository.saveBook(book.copy(coverPath = coverFile.absolutePath))
            }
        }
    }

    companion object {
        private const val UNIQUE_NAME = "kavita-sync"

        fun schedulePeriodic(context: Context) {
            val request = PeriodicWorkRequestBuilder<SyncWorker>(
                ServerConfig.SYNC_INTERVAL_MINUTES,
                TimeUnit.MINUTES,
            )
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build(),
                )
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 1, TimeUnit.MINUTES)
                .build()
            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                UNIQUE_NAME,
                ExistingPeriodicWorkPolicy.UPDATE,
                request,
            )
        }
    }
}
