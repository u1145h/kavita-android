package com.u1145h.kavitaandroid.`data`.local.db.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.u1145h.kavitaandroid.`data`.local.db.entity.BookEntity
import javax.`annotation`.processing.Generated
import kotlin.Boolean
import kotlin.Float
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.Unit
import kotlin.collections.List
import kotlin.collections.MutableList
import kotlin.collections.mutableListOf
import kotlin.reflect.KClass
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BookDao_Impl(
  __db: RoomDatabase,
) : BookDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBookEntity: EntityInsertAdapter<BookEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfBookEntity = object : EntityInsertAdapter<BookEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR REPLACE INTO `books` (`id`,`chapterId`,`seriesId`,`volumeId`,`libraryId`,`title`,`author`,`series`,`format`,`filePath`,`coverPath`,`coverUrl`,`fileSizeBytes`,`totalPages`,`currentPage`,`progressPercent`,`bookScrollId`,`timeSpentReadingMs`,`downloadDateUtc`,`lastOpenedUtc`,`lastModifiedUtc`,`progressDirty`,`serverProgressLastModifiedUtc`,`isDeleted`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BookEntity) {
        statement.bindLong(1, entity.id)
        val _tmpChapterId: Int? = entity.chapterId
        if (_tmpChapterId == null) {
          statement.bindNull(2)
        } else {
          statement.bindLong(2, _tmpChapterId.toLong())
        }
        val _tmpSeriesId: Int? = entity.seriesId
        if (_tmpSeriesId == null) {
          statement.bindNull(3)
        } else {
          statement.bindLong(3, _tmpSeriesId.toLong())
        }
        val _tmpVolumeId: Int? = entity.volumeId
        if (_tmpVolumeId == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpVolumeId.toLong())
        }
        val _tmpLibraryId: Int? = entity.libraryId
        if (_tmpLibraryId == null) {
          statement.bindNull(5)
        } else {
          statement.bindLong(5, _tmpLibraryId.toLong())
        }
        statement.bindText(6, entity.title)
        statement.bindText(7, entity.author)
        statement.bindText(8, entity.series)
        statement.bindText(9, entity.format)
        statement.bindText(10, entity.filePath)
        val _tmpCoverPath: String? = entity.coverPath
        if (_tmpCoverPath == null) {
          statement.bindNull(11)
        } else {
          statement.bindText(11, _tmpCoverPath)
        }
        val _tmpCoverUrl: String? = entity.coverUrl
        if (_tmpCoverUrl == null) {
          statement.bindNull(12)
        } else {
          statement.bindText(12, _tmpCoverUrl)
        }
        statement.bindLong(13, entity.fileSizeBytes)
        statement.bindLong(14, entity.totalPages.toLong())
        statement.bindLong(15, entity.currentPage.toLong())
        statement.bindDouble(16, entity.progressPercent.toDouble())
        val _tmpBookScrollId: String? = entity.bookScrollId
        if (_tmpBookScrollId == null) {
          statement.bindNull(17)
        } else {
          statement.bindText(17, _tmpBookScrollId)
        }
        statement.bindLong(18, entity.timeSpentReadingMs)
        statement.bindLong(19, entity.downloadDateUtc)
        val _tmpLastOpenedUtc: Long? = entity.lastOpenedUtc
        if (_tmpLastOpenedUtc == null) {
          statement.bindNull(20)
        } else {
          statement.bindLong(20, _tmpLastOpenedUtc)
        }
        statement.bindLong(21, entity.lastModifiedUtc)
        val _tmp: Int = if (entity.progressDirty) 1 else 0
        statement.bindLong(22, _tmp.toLong())
        val _tmpServerProgressLastModifiedUtc: Long? = entity.serverProgressLastModifiedUtc
        if (_tmpServerProgressLastModifiedUtc == null) {
          statement.bindNull(23)
        } else {
          statement.bindLong(23, _tmpServerProgressLastModifiedUtc)
        }
        val _tmp_1: Int = if (entity.isDeleted) 1 else 0
        statement.bindLong(24, _tmp_1.toLong())
      }
    }
  }

  public override suspend fun upsert(book: BookEntity): Long = performSuspending(__db, false, true)
      { _connection ->
    val _result: Long = __insertAdapterOfBookEntity.insertAndReturnId(_connection, book)
    _result
  }

  public override suspend fun upsertAll(books: List<BookEntity>): Unit = performSuspending(__db,
      false, true) { _connection ->
    __insertAdapterOfBookEntity.insert(_connection, books)
  }

  public override fun observeAll(): Flow<List<BookEntity>> {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 ORDER BY downloadDateUtc DESC"
    return createFlow(__db, false, arrayOf("books")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _item =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getAll(): List<BookEntity> {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _item =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeById(id: Long): Flow<BookEntity?> {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 AND id = ?"
    return createFlow(__db, false, arrayOf("books")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: BookEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _result =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getById(id: Long): BookEntity? {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 AND id = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, id)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: BookEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _result =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getByChapterId(chapterId: Int): BookEntity? {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 AND chapterId = ? LIMIT 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, chapterId.toLong())
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: BookEntity?
        if (_stmt.step()) {
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _result =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
        } else {
          _result = null
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeContinueReading(): Flow<List<BookEntity>> {
    val _sql: String =
        "SELECT * FROM books WHERE isDeleted = 0 AND progressPercent > 0 AND progressPercent < 100 ORDER BY lastOpenedUtc DESC"
    return createFlow(__db, false, arrayOf("books")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _item =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeRecentlyAdded(): Flow<List<BookEntity>> {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 ORDER BY downloadDateUtc DESC"
    return createFlow(__db, false, arrayOf("books")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _item =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeRecentlyRead(): Flow<List<BookEntity>> {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 ORDER BY lastOpenedUtc DESC"
    return createFlow(__db, false, arrayOf("books")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _item =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeStats(): Flow<StorageStats> {
    val _sql: String =
        "SELECT COUNT(*) AS bookCount, COALESCE(SUM(fileSizeBytes), 0) AS bytes FROM books WHERE isDeleted = 0"
    return createFlow(__db, false, arrayOf("books")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfBookCount: Int = 0
        val _columnIndexOfTotalBytes: Int = 1
        val _result: StorageStats
        if (_stmt.step()) {
          val _tmpBookCount: Int
          _tmpBookCount = _stmt.getLong(_columnIndexOfBookCount).toInt()
          val _tmpTotalBytes: Long
          _tmpTotalBytes = _stmt.getLong(_columnIndexOfTotalBytes)
          _result = StorageStats(_tmpBookCount,_tmpTotalBytes)
        } else {
          error("The query result was empty, but expected a single row to return a NON-NULL object of type <com.u1145h.kavitaandroid.`data`.local.db.dao.StorageStats>.")
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getDirty(): List<BookEntity> {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 AND progressDirty = 1"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _item =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun getMissingCovers(): List<BookEntity> {
    val _sql: String = "SELECT * FROM books WHERE isDeleted = 0 AND coverPath IS NULL"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfChapterId: Int = getColumnIndexOrThrow(_stmt, "chapterId")
        val _columnIndexOfSeriesId: Int = getColumnIndexOrThrow(_stmt, "seriesId")
        val _columnIndexOfVolumeId: Int = getColumnIndexOrThrow(_stmt, "volumeId")
        val _columnIndexOfLibraryId: Int = getColumnIndexOrThrow(_stmt, "libraryId")
        val _columnIndexOfTitle: Int = getColumnIndexOrThrow(_stmt, "title")
        val _columnIndexOfAuthor: Int = getColumnIndexOrThrow(_stmt, "author")
        val _columnIndexOfSeries: Int = getColumnIndexOrThrow(_stmt, "series")
        val _columnIndexOfFormat: Int = getColumnIndexOrThrow(_stmt, "format")
        val _columnIndexOfFilePath: Int = getColumnIndexOrThrow(_stmt, "filePath")
        val _columnIndexOfCoverPath: Int = getColumnIndexOrThrow(_stmt, "coverPath")
        val _columnIndexOfCoverUrl: Int = getColumnIndexOrThrow(_stmt, "coverUrl")
        val _columnIndexOfFileSizeBytes: Int = getColumnIndexOrThrow(_stmt, "fileSizeBytes")
        val _columnIndexOfTotalPages: Int = getColumnIndexOrThrow(_stmt, "totalPages")
        val _columnIndexOfCurrentPage: Int = getColumnIndexOrThrow(_stmt, "currentPage")
        val _columnIndexOfProgressPercent: Int = getColumnIndexOrThrow(_stmt, "progressPercent")
        val _columnIndexOfBookScrollId: Int = getColumnIndexOrThrow(_stmt, "bookScrollId")
        val _columnIndexOfTimeSpentReadingMs: Int = getColumnIndexOrThrow(_stmt,
            "timeSpentReadingMs")
        val _columnIndexOfDownloadDateUtc: Int = getColumnIndexOrThrow(_stmt, "downloadDateUtc")
        val _columnIndexOfLastOpenedUtc: Int = getColumnIndexOrThrow(_stmt, "lastOpenedUtc")
        val _columnIndexOfLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt, "lastModifiedUtc")
        val _columnIndexOfProgressDirty: Int = getColumnIndexOrThrow(_stmt, "progressDirty")
        val _columnIndexOfServerProgressLastModifiedUtc: Int = getColumnIndexOrThrow(_stmt,
            "serverProgressLastModifiedUtc")
        val _columnIndexOfIsDeleted: Int = getColumnIndexOrThrow(_stmt, "isDeleted")
        val _result: MutableList<BookEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpChapterId: Int?
          if (_stmt.isNull(_columnIndexOfChapterId)) {
            _tmpChapterId = null
          } else {
            _tmpChapterId = _stmt.getLong(_columnIndexOfChapterId).toInt()
          }
          val _tmpSeriesId: Int?
          if (_stmt.isNull(_columnIndexOfSeriesId)) {
            _tmpSeriesId = null
          } else {
            _tmpSeriesId = _stmt.getLong(_columnIndexOfSeriesId).toInt()
          }
          val _tmpVolumeId: Int?
          if (_stmt.isNull(_columnIndexOfVolumeId)) {
            _tmpVolumeId = null
          } else {
            _tmpVolumeId = _stmt.getLong(_columnIndexOfVolumeId).toInt()
          }
          val _tmpLibraryId: Int?
          if (_stmt.isNull(_columnIndexOfLibraryId)) {
            _tmpLibraryId = null
          } else {
            _tmpLibraryId = _stmt.getLong(_columnIndexOfLibraryId).toInt()
          }
          val _tmpTitle: String
          _tmpTitle = _stmt.getText(_columnIndexOfTitle)
          val _tmpAuthor: String
          _tmpAuthor = _stmt.getText(_columnIndexOfAuthor)
          val _tmpSeries: String
          _tmpSeries = _stmt.getText(_columnIndexOfSeries)
          val _tmpFormat: String
          _tmpFormat = _stmt.getText(_columnIndexOfFormat)
          val _tmpFilePath: String
          _tmpFilePath = _stmt.getText(_columnIndexOfFilePath)
          val _tmpCoverPath: String?
          if (_stmt.isNull(_columnIndexOfCoverPath)) {
            _tmpCoverPath = null
          } else {
            _tmpCoverPath = _stmt.getText(_columnIndexOfCoverPath)
          }
          val _tmpCoverUrl: String?
          if (_stmt.isNull(_columnIndexOfCoverUrl)) {
            _tmpCoverUrl = null
          } else {
            _tmpCoverUrl = _stmt.getText(_columnIndexOfCoverUrl)
          }
          val _tmpFileSizeBytes: Long
          _tmpFileSizeBytes = _stmt.getLong(_columnIndexOfFileSizeBytes)
          val _tmpTotalPages: Int
          _tmpTotalPages = _stmt.getLong(_columnIndexOfTotalPages).toInt()
          val _tmpCurrentPage: Int
          _tmpCurrentPage = _stmt.getLong(_columnIndexOfCurrentPage).toInt()
          val _tmpProgressPercent: Float
          _tmpProgressPercent = _stmt.getDouble(_columnIndexOfProgressPercent).toFloat()
          val _tmpBookScrollId: String?
          if (_stmt.isNull(_columnIndexOfBookScrollId)) {
            _tmpBookScrollId = null
          } else {
            _tmpBookScrollId = _stmt.getText(_columnIndexOfBookScrollId)
          }
          val _tmpTimeSpentReadingMs: Long
          _tmpTimeSpentReadingMs = _stmt.getLong(_columnIndexOfTimeSpentReadingMs)
          val _tmpDownloadDateUtc: Long
          _tmpDownloadDateUtc = _stmt.getLong(_columnIndexOfDownloadDateUtc)
          val _tmpLastOpenedUtc: Long?
          if (_stmt.isNull(_columnIndexOfLastOpenedUtc)) {
            _tmpLastOpenedUtc = null
          } else {
            _tmpLastOpenedUtc = _stmt.getLong(_columnIndexOfLastOpenedUtc)
          }
          val _tmpLastModifiedUtc: Long
          _tmpLastModifiedUtc = _stmt.getLong(_columnIndexOfLastModifiedUtc)
          val _tmpProgressDirty: Boolean
          val _tmp: Int
          _tmp = _stmt.getLong(_columnIndexOfProgressDirty).toInt()
          _tmpProgressDirty = _tmp != 0
          val _tmpServerProgressLastModifiedUtc: Long?
          if (_stmt.isNull(_columnIndexOfServerProgressLastModifiedUtc)) {
            _tmpServerProgressLastModifiedUtc = null
          } else {
            _tmpServerProgressLastModifiedUtc =
                _stmt.getLong(_columnIndexOfServerProgressLastModifiedUtc)
          }
          val _tmpIsDeleted: Boolean
          val _tmp_1: Int
          _tmp_1 = _stmt.getLong(_columnIndexOfIsDeleted).toInt()
          _tmpIsDeleted = _tmp_1 != 0
          _item =
              BookEntity(_tmpId,_tmpChapterId,_tmpSeriesId,_tmpVolumeId,_tmpLibraryId,_tmpTitle,_tmpAuthor,_tmpSeries,_tmpFormat,_tmpFilePath,_tmpCoverPath,_tmpCoverUrl,_tmpFileSizeBytes,_tmpTotalPages,_tmpCurrentPage,_tmpProgressPercent,_tmpBookScrollId,_tmpTimeSpentReadingMs,_tmpDownloadDateUtc,_tmpLastOpenedUtc,_tmpLastModifiedUtc,_tmpProgressDirty,_tmpServerProgressLastModifiedUtc,_tmpIsDeleted)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun updateProgress(
    id: Long,
    page: Int,
    percent: Float,
    modified: Long,
  ) {
    val _sql: String =
        "UPDATE books SET currentPage = ?, progressPercent = ?, lastModifiedUtc = ?, progressDirty = 1 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, page.toLong())
        _argIndex = 2
        _stmt.bindDouble(_argIndex, percent.toDouble())
        _argIndex = 3
        _stmt.bindLong(_argIndex, modified)
        _argIndex = 4
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun addReadingTime(id: Long, ms: Long) {
    val _sql: String = "UPDATE books SET timeSpentReadingMs = timeSpentReadingMs + ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, ms)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markOpened(id: Long, ts: Long) {
    val _sql: String = "UPDATE books SET lastOpenedUtc = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, ts)
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markSynced(id: Long, ts: Long?) {
    val _sql: String =
        "UPDATE books SET serverProgressLastModifiedUtc = ?, progressDirty = 0 WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        if (ts == null) {
          _stmt.bindNull(_argIndex)
        } else {
          _stmt.bindLong(_argIndex, ts)
        }
        _argIndex = 2
        _stmt.bindLong(_argIndex, id)
        _stmt.step()
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun markDeleted(id: Long) {
    val _sql: String = "UPDATE books SET isDeleted = 1 WHERE id = ?"
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
