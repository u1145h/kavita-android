package com.u1145h.kavitaandroid.work;

import android.content.Context;
import com.u1145h.kavitaandroid.data.local.db.dao.DownloadQueueDao;
import com.u1145h.kavitaandroid.data.local.files.BookFileManager;
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager;
import com.u1145h.kavitaandroid.data.repository.BookRepository;
import com.u1145h.kavitaandroid.data.repository.KavitaRepository;
import com.u1145h.kavitaandroid.data.repository.SettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DownloadImporter_Factory implements Factory<DownloadImporter> {
  private final Provider<Context> contextProvider;

  private final Provider<DownloadQueueDao> downloadQueueDaoProvider;

  private final Provider<BookRepository> bookRepositoryProvider;

  private final Provider<KavitaRepository> kavitaRepositoryProvider;

  private final Provider<BookFileManager> fileManagerProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private DownloadImporter_Factory(Provider<Context> contextProvider,
      Provider<DownloadQueueDao> downloadQueueDaoProvider,
      Provider<BookRepository> bookRepositoryProvider,
      Provider<KavitaRepository> kavitaRepositoryProvider,
      Provider<BookFileManager> fileManagerProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.contextProvider = contextProvider;
    this.downloadQueueDaoProvider = downloadQueueDaoProvider;
    this.bookRepositoryProvider = bookRepositoryProvider;
    this.kavitaRepositoryProvider = kavitaRepositoryProvider;
    this.fileManagerProvider = fileManagerProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public DownloadImporter get() {
    return newInstance(contextProvider.get(), downloadQueueDaoProvider.get(), bookRepositoryProvider.get(), kavitaRepositoryProvider.get(), fileManagerProvider.get(), settingsRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static DownloadImporter_Factory create(Provider<Context> contextProvider,
      Provider<DownloadQueueDao> downloadQueueDaoProvider,
      Provider<BookRepository> bookRepositoryProvider,
      Provider<KavitaRepository> kavitaRepositoryProvider,
      Provider<BookFileManager> fileManagerProvider,
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new DownloadImporter_Factory(contextProvider, downloadQueueDaoProvider, bookRepositoryProvider, kavitaRepositoryProvider, fileManagerProvider, settingsRepositoryProvider, sessionManagerProvider);
  }

  public static DownloadImporter newInstance(Context context, DownloadQueueDao downloadQueueDao,
      BookRepository bookRepository, KavitaRepository kavitaRepository, BookFileManager fileManager,
      SettingsRepository settingsRepository, SessionManager sessionManager) {
    return new DownloadImporter(context, downloadQueueDao, bookRepository, kavitaRepository, fileManager, settingsRepository, sessionManager);
  }
}
