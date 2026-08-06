package com.u1145h.kavitaandroid.`data`.local.db

import androidx.room.InvalidationTracker
import androidx.room.RoomOpenDelegate
import androidx.room.migration.AutoMigrationSpec
import androidx.room.migration.Migration
import androidx.room.util.TableInfo
import androidx.room.util.TableInfo.Companion.read
import androidx.room.util.dropFtsSyncTriggers
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.execSQL
import com.u1145h.kavitaandroid.`data`.local.db.dao.BookDao
import com.u1145h.kavitaandroid.`data`.local.db.dao.BookDao_Impl
import com.u1145h.kavitaandroid.`data`.local.db.dao.BookmarkDao
import com.u1145h.kavitaandroid.`data`.local.db.dao.BookmarkDao_Impl
import com.u1145h.kavitaandroid.`data`.local.db.dao.DownloadQueueDao
import com.u1145h.kavitaandroid.`data`.local.db.dao.DownloadQueueDao_Impl
import com.u1145h.kavitaandroid.`data`.local.db.dao.ReadingSessionDao
import com.u1145h.kavitaandroid.`data`.local.db.dao.ReadingSessionDao_Impl
import javax.`annotation`.processing.Generated
import kotlin.Lazy
import kotlin.String
import kotlin.Suppress
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.MutableList
import kotlin.collections.MutableMap
import kotlin.collections.MutableSet
import kotlin.collections.Set
import kotlin.collections.mutableListOf
import kotlin.collections.mutableMapOf
import kotlin.collections.mutableSetOf
import kotlin.reflect.KClass

@Generated(value = ["androidx.room.RoomProcessor"])
@Suppress(names = ["UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"])
public class KavitaDatabase_Impl : KavitaDatabase() {
  private val _bookDao: Lazy<BookDao> = lazy {
    BookDao_Impl(this)
  }

  private val _readingSessionDao: Lazy<ReadingSessionDao> = lazy {
    ReadingSessionDao_Impl(this)
  }

  private val _bookmarkDao: Lazy<BookmarkDao> = lazy {
    BookmarkDao_Impl(this)
  }

  private val _downloadQueueDao: Lazy<DownloadQueueDao> = lazy {
    DownloadQueueDao_Impl(this)
  }

  protected override fun createOpenDelegate(): RoomOpenDelegate {
    val _openDelegate: RoomOpenDelegate = object : RoomOpenDelegate(1,
        "8e475518cf004b294bc96c9837ba215f", "2b96a03d7dd41b854baf12051b4f41da") {
      public override fun createAllTables(connection: SQLiteConnection) {
        connection.execSQL("CREATE TABLE IF NOT EXISTS `books` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `chapterId` INTEGER, `seriesId` INTEGER, `volumeId` INTEGER, `libraryId` INTEGER, `title` TEXT NOT NULL, `author` TEXT NOT NULL, `series` TEXT NOT NULL, `format` TEXT NOT NULL, `filePath` TEXT NOT NULL, `coverPath` TEXT, `coverUrl` TEXT, `fileSizeBytes` INTEGER NOT NULL, `totalPages` INTEGER NOT NULL, `currentPage` INTEGER NOT NULL, `progressPercent` REAL NOT NULL, `bookScrollId` TEXT, `timeSpentReadingMs` INTEGER NOT NULL, `downloadDateUtc` INTEGER NOT NULL, `lastOpenedUtc` INTEGER, `lastModifiedUtc` INTEGER NOT NULL, `progressDirty` INTEGER NOT NULL, `serverProgressLastModifiedUtc` INTEGER, `isDeleted` INTEGER NOT NULL)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_books_chapterId` ON `books` (`chapterId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_books_seriesId` ON `books` (`seriesId`)")
        connection.execSQL("CREATE INDEX IF NOT EXISTS `index_books_isDeleted_progressDirty` ON `books` (`isDeleted`, `progressDirty`)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `reading_sessions` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `bookId` INTEGER NOT NULL, `startedAtUtc` INTEGER NOT NULL, `endedAtUtc` INTEGER, `durationMs` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `bookmarks` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `bookId` INTEGER NOT NULL, `page` INTEGER NOT NULL, `location` TEXT, `note` TEXT NOT NULL, `createdAtUtc` INTEGER NOT NULL)")
        connection.execSQL("CREATE TABLE IF NOT EXISTS `download_queue` (`downloadId` INTEGER NOT NULL, `chapterId` INTEGER, `volumeId` INTEGER, `seriesId` INTEGER, `libraryId` INTEGER, `title` TEXT NOT NULL, `filename` TEXT, `status` TEXT NOT NULL, `createdAtUtc` INTEGER NOT NULL, PRIMARY KEY(`downloadId`))")
        connection.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)")
        connection.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '8e475518cf004b294bc96c9837ba215f')")
      }

      public override fun dropAllTables(connection: SQLiteConnection) {
        connection.execSQL("DROP TABLE IF EXISTS `books`")
        connection.execSQL("DROP TABLE IF EXISTS `reading_sessions`")
        connection.execSQL("DROP TABLE IF EXISTS `bookmarks`")
        connection.execSQL("DROP TABLE IF EXISTS `download_queue`")
      }

      public override fun onCreate(connection: SQLiteConnection) {
      }

      public override fun onOpen(connection: SQLiteConnection) {
        internalInitInvalidationTracker(connection)
      }

      public override fun onPreMigrate(connection: SQLiteConnection) {
        dropFtsSyncTriggers(connection)
      }

      public override fun onPostMigrate(connection: SQLiteConnection) {
      }

      public override fun onValidateSchema(connection: SQLiteConnection):
          RoomOpenDelegate.ValidationResult {
        val _columnsBooks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBooks.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("chapterId", TableInfo.Column("chapterId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("seriesId", TableInfo.Column("seriesId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("volumeId", TableInfo.Column("volumeId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("libraryId", TableInfo.Column("libraryId", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("author", TableInfo.Column("author", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("series", TableInfo.Column("series", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("format", TableInfo.Column("format", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("filePath", TableInfo.Column("filePath", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("coverPath", TableInfo.Column("coverPath", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("coverUrl", TableInfo.Column("coverUrl", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("fileSizeBytes", TableInfo.Column("fileSizeBytes", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("totalPages", TableInfo.Column("totalPages", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("currentPage", TableInfo.Column("currentPage", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("progressPercent", TableInfo.Column("progressPercent", "REAL", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("bookScrollId", TableInfo.Column("bookScrollId", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("timeSpentReadingMs", TableInfo.Column("timeSpentReadingMs", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("downloadDateUtc", TableInfo.Column("downloadDateUtc", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("lastOpenedUtc", TableInfo.Column("lastOpenedUtc", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("lastModifiedUtc", TableInfo.Column("lastModifiedUtc", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("progressDirty", TableInfo.Column("progressDirty", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("serverProgressLastModifiedUtc",
            TableInfo.Column("serverProgressLastModifiedUtc", "INTEGER", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBooks.put("isDeleted", TableInfo.Column("isDeleted", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBooks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBooks: MutableSet<TableInfo.Index> = mutableSetOf()
        _indicesBooks.add(TableInfo.Index("index_books_chapterId", false, listOf("chapterId"),
            listOf("ASC")))
        _indicesBooks.add(TableInfo.Index("index_books_seriesId", false, listOf("seriesId"),
            listOf("ASC")))
        _indicesBooks.add(TableInfo.Index("index_books_isDeleted_progressDirty", false,
            listOf("isDeleted", "progressDirty"), listOf("ASC", "ASC")))
        val _infoBooks: TableInfo = TableInfo("books", _columnsBooks, _foreignKeysBooks,
            _indicesBooks)
        val _existingBooks: TableInfo = read(connection, "books")
        if (!_infoBooks.equals(_existingBooks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |books(com.u1145h.kavitaandroid.data.local.db.entity.BookEntity).
              | Expected:
              |""".trimMargin() + _infoBooks + """
              |
              | Found:
              |""".trimMargin() + _existingBooks)
        }
        val _columnsReadingSessions: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsReadingSessions.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingSessions.put("bookId", TableInfo.Column("bookId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingSessions.put("startedAtUtc", TableInfo.Column("startedAtUtc", "INTEGER",
            true, 0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingSessions.put("endedAtUtc", TableInfo.Column("endedAtUtc", "INTEGER", false,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        _columnsReadingSessions.put("durationMs", TableInfo.Column("durationMs", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysReadingSessions: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesReadingSessions: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoReadingSessions: TableInfo = TableInfo("reading_sessions", _columnsReadingSessions,
            _foreignKeysReadingSessions, _indicesReadingSessions)
        val _existingReadingSessions: TableInfo = read(connection, "reading_sessions")
        if (!_infoReadingSessions.equals(_existingReadingSessions)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |reading_sessions(com.u1145h.kavitaandroid.data.local.db.entity.ReadingSessionEntity).
              | Expected:
              |""".trimMargin() + _infoReadingSessions + """
              |
              | Found:
              |""".trimMargin() + _existingReadingSessions)
        }
        val _columnsBookmarks: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsBookmarks.put("id", TableInfo.Column("id", "INTEGER", true, 1, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("bookId", TableInfo.Column("bookId", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("page", TableInfo.Column("page", "INTEGER", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("location", TableInfo.Column("location", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("note", TableInfo.Column("note", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsBookmarks.put("createdAtUtc", TableInfo.Column("createdAtUtc", "INTEGER", true, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysBookmarks: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesBookmarks: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoBookmarks: TableInfo = TableInfo("bookmarks", _columnsBookmarks,
            _foreignKeysBookmarks, _indicesBookmarks)
        val _existingBookmarks: TableInfo = read(connection, "bookmarks")
        if (!_infoBookmarks.equals(_existingBookmarks)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |bookmarks(com.u1145h.kavitaandroid.data.local.db.entity.BookmarkEntity).
              | Expected:
              |""".trimMargin() + _infoBookmarks + """
              |
              | Found:
              |""".trimMargin() + _existingBookmarks)
        }
        val _columnsDownloadQueue: MutableMap<String, TableInfo.Column> = mutableMapOf()
        _columnsDownloadQueue.put("downloadId", TableInfo.Column("downloadId", "INTEGER", true, 1,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("chapterId", TableInfo.Column("chapterId", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("volumeId", TableInfo.Column("volumeId", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("seriesId", TableInfo.Column("seriesId", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("libraryId", TableInfo.Column("libraryId", "INTEGER", false, 0,
            null, TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("title", TableInfo.Column("title", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("filename", TableInfo.Column("filename", "TEXT", false, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("status", TableInfo.Column("status", "TEXT", true, 0, null,
            TableInfo.CREATED_FROM_ENTITY))
        _columnsDownloadQueue.put("createdAtUtc", TableInfo.Column("createdAtUtc", "INTEGER", true,
            0, null, TableInfo.CREATED_FROM_ENTITY))
        val _foreignKeysDownloadQueue: MutableSet<TableInfo.ForeignKey> = mutableSetOf()
        val _indicesDownloadQueue: MutableSet<TableInfo.Index> = mutableSetOf()
        val _infoDownloadQueue: TableInfo = TableInfo("download_queue", _columnsDownloadQueue,
            _foreignKeysDownloadQueue, _indicesDownloadQueue)
        val _existingDownloadQueue: TableInfo = read(connection, "download_queue")
        if (!_infoDownloadQueue.equals(_existingDownloadQueue)) {
          return RoomOpenDelegate.ValidationResult(false, """
              |download_queue(com.u1145h.kavitaandroid.data.local.db.entity.DownloadQueueEntity).
              | Expected:
              |""".trimMargin() + _infoDownloadQueue + """
              |
              | Found:
              |""".trimMargin() + _existingDownloadQueue)
        }
        return RoomOpenDelegate.ValidationResult(true, null)
      }
    }
    return _openDelegate
  }

  protected override fun createInvalidationTracker(): InvalidationTracker {
    val _shadowTablesMap: MutableMap<String, String> = mutableMapOf()
    val _viewTables: MutableMap<String, Set<String>> = mutableMapOf()
    return InvalidationTracker(this, _shadowTablesMap, _viewTables, "books", "reading_sessions",
        "bookmarks", "download_queue")
  }

  public override fun clearAllTables() {
    super.performClear(false, "books", "reading_sessions", "bookmarks", "download_queue")
  }

  protected override fun getRequiredTypeConverterClasses(): Map<KClass<*>, List<KClass<*>>> {
    val _typeConvertersMap: MutableMap<KClass<*>, List<KClass<*>>> = mutableMapOf()
    _typeConvertersMap.put(BookDao::class, BookDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(ReadingSessionDao::class, ReadingSessionDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(BookmarkDao::class, BookmarkDao_Impl.getRequiredConverters())
    _typeConvertersMap.put(DownloadQueueDao::class, DownloadQueueDao_Impl.getRequiredConverters())
    return _typeConvertersMap
  }

  public override fun getRequiredAutoMigrationSpecClasses(): Set<KClass<out AutoMigrationSpec>> {
    val _autoMigrationSpecsSet: MutableSet<KClass<out AutoMigrationSpec>> = mutableSetOf()
    return _autoMigrationSpecsSet
  }

  public override
      fun createAutoMigrations(autoMigrationSpecs: Map<KClass<out AutoMigrationSpec>, AutoMigrationSpec>):
      List<Migration> {
    val _autoMigrations: MutableList<Migration> = mutableListOf()
    return _autoMigrations
  }

  public override fun bookDao(): BookDao = _bookDao.value

  public override fun readingSessionDao(): ReadingSessionDao = _readingSessionDao.value

  public override fun bookmarkDao(): BookmarkDao = _bookmarkDao.value

  public override fun downloadQueueDao(): DownloadQueueDao = _downloadQueueDao.value
}
