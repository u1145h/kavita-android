package com.u1145h.kavitaandroid.feature.reader;

import androidx.lifecycle.SavedStateHandle;
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
public final class ReaderViewModel_Factory implements Factory<ReaderViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<BookRepository> bookRepositoryProvider;

  private ReaderViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.bookRepositoryProvider = bookRepositoryProvider;
  }

  @Override
  public ReaderViewModel get() {
    return newInstance(savedStateHandleProvider.get(), bookRepositoryProvider.get());
  }

  public static ReaderViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    return new ReaderViewModel_Factory(savedStateHandleProvider, bookRepositoryProvider);
  }

  public static ReaderViewModel newInstance(SavedStateHandle savedStateHandle,
      BookRepository bookRepository) {
    return new ReaderViewModel(savedStateHandle, bookRepository);
  }
}
