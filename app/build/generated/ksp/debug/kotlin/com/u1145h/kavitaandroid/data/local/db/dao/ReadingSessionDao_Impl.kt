package com.u1145h.kavitaandroid.`data`.local.db.dao

import androidx.room.EntityInsertAdapter
import androidx.room.RoomDatabase
import androidx.room.util.performSuspending
import androidx.sqlite.SQLiteStatement
import com.u1145h.kavitaandroid.`data`.local.db.entity.ReadingSessionEntity
import javax.`annotation`.processing.Generated
import kotlin.Int
import kotlin.Long
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class ReadingSessionDao_Impl(
  __db: RoomDatabase,
) : ReadingSessionDao {
  private val __db: RoomDatabase

  private val __insertAdapterOfReadingSessionEntity: EntityInsertAdapter<ReadingSessionEntity>
  init {
    this.__db = __db
    this.__insertAdapterOfReadingSessionEntity = object :
        EntityInsertAdapter<ReadingSessionEntity>() {
      protected override fun createQuery(): String =
          "INSERT OR ABORT INTO `reading_sessions` (`id`,`bookId`,`startedAtUtc`,`endedAtUtc`,`durationMs`) VALUES (nullif(?, 0),?,?,?,?)"

      protected override fun bind(statement: SQLiteStatement, entity: ReadingSessionEntity) {
        statement.bindLong(1, entity.id)
        statement.bindLong(2, entity.bookId)
        statement.bindLong(3, entity.startedAtUtc)
        val _tmpEndedAtUtc: Long? = entity.endedAtUtc
        if (_tmpEndedAtUtc == null) {
          statement.bindNull(4)
        } else {
          statement.bindLong(4, _tmpEndedAtUtc)
        }
        statement.bindLong(5, entity.durationMs)
      }
    }
  }

  public override suspend fun insert(session: ReadingSessionEntity): Long = performSuspending(__db,
      false, true) { _connection ->
    val _result: Long = __insertAdapterOfReadingSessionEntity.insertAndReturnId(_connection,
        session)
    _result
  }

  public override suspend fun totalDuration(bookId: Long): Long {
    val _sql: String = "SELECT COALESCE(SUM(durationMs), 0) FROM reading_sessions WHERE bookId = ?"
    return performSuspending(__db, true, false) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, bookId)
        val _result: Long
        if (_stmt.step()) {
          val _tmp: Long
          _tmp = _stmt.getLong(0)
          _result = _tmp
        } else {
          _result = 0L
        }
        _result
      } finally {
        _stmt.close()
      }
    }
  }

  public override suspend fun close(
    id: Long,
    end: Long,
    ms: Long,
  ) {
    val _sql: String = "UPDATE reading_sessions SET endedAtUtc = ?, durationMs = ? WHERE id = ?"
    return performSuspending(__db, false, true) { _connection ->
      val _stmt: SQLiteStatement = _connection.prepare(_sql)
      try {
        var _argIndex: Int = 1
        _stmt.bindLong(_argIndex, end)
        _argIndex = 2
        _stmt.bindLong(_argIndex, ms)
        _argIndex = 3
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
