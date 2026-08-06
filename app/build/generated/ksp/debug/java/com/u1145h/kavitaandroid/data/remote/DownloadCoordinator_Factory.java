package com.u1145h.kavitaandroid.data.remote;

import android.app.DownloadManager;
import android.content.Context;
import com.u1145h.kavitaandroid.data.local.db.dao.DownloadQueueDao;
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager;
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
public final class DownloadCoordinator_Factory implements Factory<DownloadCoordinator> {
  private final Provider<Context> contextProvider;

  private final Provider<DownloadManager> downloadManagerProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<DownloadQueueDao> downloadQueueDaoProvider;

  private DownloadCoordinator_Factory(Provider<Context> contextProvider,
      Provider<DownloadManager> downloadManagerProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<DownloadQueueDao> downloadQueueDaoProvider) {
    this.contextProvider = contextProvider;
    this.downloadManagerProvider = downloadManagerProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.downloadQueueDaoProvider = downloadQueueDaoProvider;
  }

  @Override
  public DownloadCoordinator get() {
    return newInstance(contextProvider.get(), downloadManagerProvider.get(), sessionManagerProvider.get(), downloadQueueDaoProvider.get());
  }

  public static DownloadCoordinator_Factory create(Provider<Context> contextProvider,
      Provider<DownloadManager> downloadManagerProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<DownloadQueueDao> downloadQueueDaoProvider) {
    return new DownloadCoordinator_Factory(contextProvider, downloadManagerProvider, sessionManagerProvider, downloadQueueDaoProvider);
  }

  public static DownloadCoordinator newInstance(Context context, DownloadManager downloadManager,
      SessionManager sessionManager, DownloadQueueDao downloadQueueDao) {
    return new DownloadCoordinator(context, downloadManager, sessionManager, downloadQueueDao);
  }
}
