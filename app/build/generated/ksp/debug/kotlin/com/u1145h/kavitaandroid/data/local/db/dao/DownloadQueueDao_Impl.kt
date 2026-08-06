package com.u1145h.kavitaandroid.`data`.local.db.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.u1145h.kavitaandroid.`data`.local.db.entity.DownloadQueueEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class DownloadQueueDao_Impl(
  __db: RoomDatabase,
) : DownloadQueueDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfDownloadQueueEntity: EntityInsertAdapter<DownloadQueueEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfDownloadQueueEntity = object : EntityInsertAdapter<DownloadQueueEntity>()
        {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `download_queue` (`downloadId`,`chapterId`,`volumeId`,`seriesId`,`libraryId`,`title`,`filename`,`status`,`createdAtUtc`) VALUES (?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: DownloadQueueEntity) {
        statement.bindLong(1, entity.downloadId)
        val _tmpChapterId: Int? = entity.chapterId
        if (_tmpChapterId == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpChapterId.toLong())
        }
        val _tmpVolumeId: Int? = entity.volumeId
        if (_tmpVolumeId == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpVolumeId.toLong())
        }
        val _tmpSeriesId: Int? = entity.seriesId
        if (_tmpSeriesId == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpSeriesId.toLong())
        }
        val _tmpLibraryId: Int? = entity.libraryId
        if (_tmpLibraryId == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpLibraryId.toLong())
        }
        statement.bindText(6, entity.title)
        val _tmpFilename: String? = entity.filename
        if (_tmpFilename == null) {
          statement.bindNull(7)
        } else {
          statement.bindText(7, _tmpFilename)
        }
        statement.bindText(8, entity.status)
        statement.bindLong(9, entity.createdAtUtc)
      }
    }
  }

  public override suspend fun upsert(item: DownloadQueueEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfDownloadQueueEntity.insert(_connection, item)
  }

  public override suspend fun `get`(id: Long): DownloadQueueEntity? {
    val _sql: String = "SELECT * FROM download_queue WHERE downloadId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfDownloadId: Int = getColumnIndexOrThrow(_stmt, "downloadId")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfFilename: Int = getColumnIndexOrThrow(_stmt, "filename")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfCreatedAtUtc: Int = getColumnIndexOrThrow(_stmt, "createdAtUtc")
        val _result: DownloadQueueEntity?
        if (_stmt.step()) {
          val _tmpDownloadId: Long
          _tmpDownloadId = _stmt.getLong(_columnIndexOfDownloadId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpFilename: String?
          if (_stmt.isNull(_columnIndexOfFilename)) {
            _tmpFilename = null
          } else {
            _tmpFilename = _stmt.getText(_columnIndexOfFilename)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpCreatedAtUtc: Long
          _tmpCreatedAtUtc = _stmt.getLong(_columnIndexOfCreatedAtUtc)
          _result =
              DownloadQueueEntity(_tmpDownloadId,_tmpChapterId,_tmpVolumeId,_tmpSeriesId,_tmpLibraryId,_tmpTitle,_tmpFilename,_tmpStatus,_tmpCreatedAtUtc)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun pending(): List<DownloadQueueEntity> {
    val _sql: String = "SELECT * FROM download_queue WHERE status = 'pending'"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfDownloadId: Int = getColumnIndexOrThrow(_stmt, "downloadId")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfFilename: Int = getColumnIndexOrThrow(_stmt, "filename")
        val _columnIndexOfStatus: Int = getColumnIndexOrThrow(_stmt, "status")
        val _columnIndexOfCreatedAtUtc: Int = getColumnIndexOrThrow(_stmt, "createdAtUtc")
        val _result: MutableList<DownloadQueueEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: DownloadQueueEntity
          val _tmpDownloadId: Long
          _tmpDownloadId = _stmt.getLong(_columnIndexOfDownloadId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpFilename: String?
          if (_stmt.isNull(_columnIndexOfFilename)) {
            _tmpFilename = null
          } else {
            _tmpFilename = _stmt.getText(_columnIndexOfFilename)
          }
          val _tmpStatus: String
          _tmpStatus = _stmt.getText(_columnIndexOfStatus)
          val _tmpCreatedAtUtc: Long
          _tmpCreatedAtUtc = _stmt.getLong(_columnIndexOfCreatedAtUtc)
          _item =
              DownloadQueueEntity(_tmpDownloadId,_tmpChapterId,_tmpVolumeId,_tmpSeriesId,_tmpLibraryId,_tmpTitle,_tmpFilename,_tmpStatus,_tmpCreatedAtUtc)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun remove(id: Long) {
    val _sql: String = "DELETE FROM download_queue WHERE downloadId = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
