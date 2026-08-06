package com.u1145h.kavitaandroid.data.repository;

import com.u1145h.kavitaandroid.data.local.db.dao.BookDao;
import com.u1145h.kavitaandroid.data.local.files.BookFileManager;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class BookRepositoryImpl_Factory implements Factory<BookRepositoryImpl> {
  private final Provider<BookDao> bookDaoProvider;

  private final Provider<BookFileManager> fileManagerProvider;

  private BookRepositoryImpl_Factory(Provider<BookDao> bookDaoProvider,
      Provider<BookFileManager> fileManagerProvider) {
    this.bookDaoProvider = bookDaoProvider;
    this.fileManagerProvider = fileManagerProvider;
  }

  @Override
  public BookRepositoryImpl get() {
    return newInstance(bookDaoProvider.get(), fileManagerProvider.get());
  }

  public static BookRepositoryImpl_Factory create(Provider<BookDao> bookDaoProvider,
      Provider<BookFileManager> fileManagerProvider) {
    return new BookRepositoryImpl_Factory(bookDaoProvider, fileManagerProvider);
  }

  public static BookRepositoryImpl newInstance(BookDao bookDao, BookFileManager fileManager) {
    return new BookRepositoryImpl(bookDao, fileManager);
  }
}
