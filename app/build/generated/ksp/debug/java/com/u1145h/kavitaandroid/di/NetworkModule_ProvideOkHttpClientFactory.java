package com.u1145h.kavitaandroid.di;

import com.u1145h.kavitaandroid.data.remote.auth.SessionManager;
import com.u1145h.kavitaandroid.data.repository.SettingsRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;

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
public final class NetworkModule_ProvideOkHttpClientFactory implements Factory<OkHttpClient> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private NetworkModule_ProvideOkHttpClientFactory(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttpClient(settingsRepositoryProvider.get(), sessionManagerProvider.get());
  }

  public static NetworkModule_ProvideOkHttpClientFactory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider) {
    return new NetworkModule_ProvideOkHttpClientFactory(settingsRepositoryProvider, sessionManagerProvider);
  }

  public static OkHttpClient provideOkHttpClient(SettingsRepository settingsRepository,
      SessionManager sessionManager) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttpClient(settingsRepository, sessionManager));
  }
}
