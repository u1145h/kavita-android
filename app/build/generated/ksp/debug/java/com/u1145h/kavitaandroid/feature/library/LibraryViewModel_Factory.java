package com.u1145h.kavitaandroid.feature.library;

import com.u1145h.kavitaandroid.data.repository.BookRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class LibraryViewModel_Factory implements Factory<LibraryViewModel> {
  private final Provider<BookRepository> bookRepositoryProvider;

  private LibraryViewModel_Factory(Provider<BookRepository> bookRepositoryProvider) {
    this.bookRepositoryProvider = bookRepositoryProvider;
  }

  @Override
  public LibraryViewModel get() {
    return newInstance(bookRepositoryProvider.get());
  }

  public static LibraryViewModel_Factory create(Provider<BookRepository> bookRepositoryProvider) {
    return new LibraryViewModel_Factory(bookRepositoryProvider);
  }

  public static LibraryViewModel newInstance(BookRepository bookRepository) {
    return new LibraryViewModel(bookRepository);
  }
}
