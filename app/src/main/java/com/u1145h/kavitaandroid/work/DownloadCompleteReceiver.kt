package com.u1145h.kavitaandroid.work

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.Looper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Listens for DownloadManager completion events and dispatches an import of
 * the finished download.
 *
 * NOTE: DownloadManager sends this broadcast to all apps; the receiver checks
 * the download status and lets [DownloadImportWorker] validate the download id
 * against the pending queue.
 */
@AndroidEntryPoint
class DownloadCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != DownloadManager.ACTION_DOWNLOAD_COMPLETE) return
        val downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)
        if (downloadId == -1L) return

        val status = queryStatus(context, downloadId)
        if (status != DownloadManager.STATUS_SUCCESSFUL) return

        // Defer slightly so the DownloadManager row is fully updated.
        Handler(Looper.getMainLooper()).postDelayed({
            DownloadImportWorker.enqueue(context.applicationContext, downloadId)
        }, 250L)
    }

    private fun queryStatus(context: Context, downloadId: Long): Int {
        val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        return runCatching {
            dm.query(DownloadManager.Query().setFilterById(downloadId)).use { cursor ->
                if (cursor.moveToFirst()) {
                    cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))
                } else {
                    DownloadManager.STATUS_FAILED
                }
            }
        }.getOrDefault(DownloadManager.STATUS_FAILED)
    }
}
