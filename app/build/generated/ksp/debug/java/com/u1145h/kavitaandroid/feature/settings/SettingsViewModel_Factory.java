package com.u1145h.kavitaandroid.feature.settings;

import com.u1145h.kavitaandroid.data.remote.auth.SessionManager;
import com.u1145h.kavitaandroid.data.repository.BookRepository;
import com.u1145h.kavitaandroid.data.repository.SettingsRepository;
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
public final class SettingsViewModel_Factory implements Factory<SettingsViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private final Provider<SessionManager> sessionManagerProvider;

  private final Provider<BookRepository> bookRepositoryProvider;

  private SettingsViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
    this.sessionManagerProvider = sessionManagerProvider;
    this.bookRepositoryProvider = bookRepositoryProvider;
  }

  @Override
  public SettingsViewModel get() {
    return newInstance(settingsRepositoryProvider.get(), sessionManagerProvider.get(), bookRepositoryProvider.get());
  }

  public static SettingsViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider,
      Provider<SessionManager> sessionManagerProvider,
      Provider<BookRepository> bookRepositoryProvider) {
    return new SettingsViewModel_Factory(settingsRepositoryProvider, sessionManagerProvider, bookRepositoryProvider);
  }

  public static SettingsViewModel newInstance(SettingsRepository settingsRepository,
      SessionManager sessionManager, BookRepository bookRepository) {
    return new SettingsViewModel(settingsRepository, sessionManager, bookRepository);
  }
}
