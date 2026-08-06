package com.u1145h.kavitaandroid.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

/**
 * Imports a single completed DownloadManager file into the offline library.
 */
@HiltWorker
class DownloadImportWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted params: WorkerParameters,
    private val downloadImporter: DownloadImporter,
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val downloadId = inputData.getLong(KEY_DOWNLOAD_ID, -1L)
        if (downloadId == -1L) return Result.failure()
        val result = downloadImporter.importCompletedDownload(downloadId)
        return if (result.isSuccess) Result.success() else Result.retry()
    }

    companion object {
        private const val KEY_DOWNLOAD_ID = "download_id"

        fun enqueue(context: Context, downloadId: Long) {
            val request = OneTimeWorkRequestBuilder<DownloadImportWorker>()
                .setInputData(workDataOf(KEY_DOWNLOAD_ID to downloadId))
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build(),
                )
                .build()
            WorkManager.getInstance(context).enqueue(request)
        }
    }
}
