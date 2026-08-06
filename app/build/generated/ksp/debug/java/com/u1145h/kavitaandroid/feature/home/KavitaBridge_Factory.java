package com.u1145h.kavitaandroid.feature.home;

import com.u1145h.kavitaandroid.data.remote.DownloadCoordinator;
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager;
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
public final class KavitaBridge_Factory implements Factory<KavitaBridge> {
  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<DownloadCoordinator> downloadCoordinatorProvider;

  private KavitaBridge_Factory(Provider<SessionManager> sessionManagerProvider,
      Provider<DownloadCoordinator> downloadCoordinatorProvider) {
    this.sessionManagerProvider = sessionManagerProvider;
    this.downloadCoordinatorProvider = downloadCoordinatorProvider;
  }

  @Override
  public KavitaBridge get() {
    return newInstance(sessionManagerProvider.get(), downloadCoordinatorProvider.get());
  }

  public static KavitaBridge_Factory create(Provider<SessionManager> sessionManagerProvider,
      Provider<DownloadCoordinator> downloadCoordinatorProvider) {
    return new KavitaBridge_Factory(sessionManagerProvider, downloadCoordinatorProvider);
  }

  public static KavitaBridge newInstance(SessionManager sessionManager,
      DownloadCoordinator downloadCoordinator) {
    return new KavitaBridge(sessionManager, downloadCoordinator);
  }
}
