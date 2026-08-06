package com.u1145h.kavitaandroid.work;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.u1145h.kavitaandroid.data.local.files.BookFileManager;
import com.u1145h.kavitaandroid.data.repository.BookRepository;
import com.u1145h.kavitaandroid.data.repository.KavitaRepository;
import com.u1145h.kavitaandroid.data.repository.SettingsRepository;
import dagger.internal.DaggerGenerated;
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
public final class SyncWorker_Factory {
  private final Provider<BookRepository> bookRepositoryProvider;

  private final Provider<KavitaRepository> kavitaRepositoryProvider;

  private final Provider<BookFileManager> fileManagerProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private SyncWorker_Factory(Provider<BookRepository> bookRepositoryProvider,
      Provider<KavitaRepository> kavitaRepositoryProvider,
      Provider<BookFileManager> fileManagerProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.bookRepositoryProvider = bookRepositoryProvider;
    this.kavitaRepositoryProvider = kavitaRepositoryProvider;
    this.fileManagerProvider = fileManagerProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  public SyncWorker get(Context appContext, WorkerParameters params) {
    return newInstance(appContext, params, bookRepositoryProvider.get(), kavitaRepositoryProvider.get(), fileManagerProvider.get(), settingsRepositoryProvider.get());
  }

  public static SyncWorker_Factory create(Provider<BookRepository> bookRepositoryProvider,
      Provider<KavitaRepository> kavitaRepositoryProvider,
      Provider<BookFileManager> fileManagerProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new SyncWorker_Factory(bookRepositoryProvider, kavitaRepositoryProvider, fileManagerProvider, settingsRepositoryProvider);
  }

  public static SyncWorker newInstance(Context appContext, WorkerParameters params,
      BookRepository bookRepository, KavitaRepository kavitaRepository, BookFileManager fileManager,
      SettingsRepository settingsRepository) {
    return new SyncWorker(appContext, params, bookRepository, kavitaRepository, fileManager, settingsRepository);
  }
}
