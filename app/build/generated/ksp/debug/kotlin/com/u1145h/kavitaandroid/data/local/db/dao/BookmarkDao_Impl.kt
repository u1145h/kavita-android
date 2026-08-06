package com.u1145h.kavitaandroid.`data`.local.db.dao

import androidx.room.EntityDeleteOrUpdateAdapter
import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.coroutines.createFlow
import androidx.room.util.getColumnIndexOrThrow
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.u1145h.kavitaandroid.`data`.local.db.entity.BookmarkEntity
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
import kotlinx.coroutines.flow.Flow

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class BookmarkDao_Impl(
  __db: RoomDatabase,
) : BookmarkDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfBookmarkEntity: EntityInsertAdapter<BookmarkEntity>

  private val __deleteAdapterOfBookmarkEntity: EntityDeleteOrUpdateAdapter<BookmarkEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfBookmarkEntity = object : EntityInsertAdapter<BookmarkEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `bookmarks` (`id`,`bookId`,`page`,`location`,`note`,`createdAtUtc`) VALUES (nullif(?, 0),?,?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: BookmarkEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.bookId)
        statement.bindLong(3, entity.page.toLong())
        val _tmpLocation: String? = entity.location
        if (_tmpLocation == null) {
          statement.bindNull(4)
        } else {
          statement.bindText(4, _tmpLocation)
        }
        statement.bindText(5, entity.note)
        statement.bindLong(6, entity.createdAtUtc)
      }
    }
    this.__deleteAdapterOfBookmarkEntity = object : EntityDeleteOrUpdateAdapter<BookmarkEntity>() {
      protected override fun createQuery(): String = "DELETE FROM `bookmarks` WHERE `id` = ?"

      protected override fun bind(statement: SQLiteStatement, entity: BookmarkEntity) {
        statement.bindLong(1, entity.id)
      }
    }
  }

  public override suspend fun insert(bookmark: BookmarkEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfBookmarkEntity.insertAndReturnId(_connection, bookmark)
    _result
  }

  public override suspend fun delete(bookmark: BookmarkEntity): Unit = performSuspending(__db,
      false, true) { _connection ->
    __deleteAdapterOfBookmarkEntity.handle(_connection, bookmark)
  }

  public override suspend fun forBook(bookId: Long): List<BookmarkEntity> {
    val _sql: String = "SELECT * FROM bookmarks WHERE bookId = ? ORDER BY page ASC"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfBookId: Int = getColumnIndexOrThrow(_stmt, "bookId")
        val _columnIndexOfPage: Int = getColumnIndexOrThrow(_stmt, "page")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfCreatedAtUtc: Int = getColumnIndexOrThrow(_stmt, "createdAtUtc")
        val _result: MutableList<BookmarkEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookmarkEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpBookId: Long
          _tmpBookId = _stmt.getLong(_columnIndexOfBookId)
          val _tmpPage: Int
          _tmpPage = _stmt.getLong(_columnIndexOfPage).toInt()
          val _tmpLocation: String?
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          }
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpCreatedAtUtc: Long
          _tmpCreatedAtUtc = _stmt.getLong(_columnIndexOfCreatedAtUtc)
          _item = BookmarkEntity(_tmpId,_tmpBookId,_tmpPage,_tmpLocation,_tmpNote,_tmpCreatedAtUtc)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override fun observeForBook(bookId: Long): Flow<List<BookmarkEntity>> {
    val _sql: String = "SELECT * FROM bookmarks WHERE bookId = ? ORDER BY page ASC"
    return createFlow(__db, false, arrayOf("bookmarks")) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId)
        val _columnIndexOfId: Int = getColumnIndexOrThrow(_stmt, "id")
        val _columnIndexOfBookId: Int = getColumnIndexOrThrow(_stmt, "bookId")
        val _columnIndexOfPage: Int = getColumnIndexOrThrow(_stmt, "page")
        val _columnIndexOfLocation: Int = getColumnIndexOrThrow(_stmt, "location")
        val _columnIndexOfNote: Int = getColumnIndexOrThrow(_stmt, "note")
        val _columnIndexOfCreatedAtUtc: Int = getColumnIndexOrThrow(_stmt, "createdAtUtc")
        val _result: MutableList<BookmarkEntity> = mutableListOf()
        while (_stmt.step()) {
          val _item: BookmarkEntity
          val _tmpId: Long
          _tmpId = _stmt.getLong(_columnIndexOfId)
          val _tmpBookId: Long
          _tmpBookId = _stmt.getLong(_columnIndexOfBookId)
          val _tmpPage: Int
          _tmpPage = _stmt.getLong(_columnIndexOfPage).toInt()
          val _tmpLocation: String?
          if (_stmt.isNull(_columnIndexOfLocation)) {
            _tmpLocation = null
          } else {
            _tmpLocation = _stmt.getText(_columnIndexOfLocation)
          }
          val _tmpNote: String
          _tmpNote = _stmt.getText(_columnIndexOfNote)
          val _tmpCreatedAtUtc: Long
          _tmpCreatedAtUtc = _stmt.getLong(_columnIndexOfCreatedAtUtc)
          _item = BookmarkEntity(_tmpId,_tmpBookId,_tmpPage,_tmpLocation,_tmpNote,_tmpCreatedAtUtc)
          _result.add(_item)
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public companion object {
    public fun getRequiredConverters(): List<KClass<*>> = emptyList()
  }
}
