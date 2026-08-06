package com.u1145h.kavitaandroid.data.repository;

import com.u1145h.kavitaandroid.data.local.datastore.SessionDataStore;
import com.u1145h.kavitaandroid.data.local.datastore.SettingsDataStore;
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
public final class SettingsRepository_Factory implements Factory<SettingsRepository> {
  private final Provider<SettingsDataStore> settingsDataStoreProvider;

  private final Provider<SessionDataStore> sessionDataStoreProvider;

  private SettingsRepository_Factory(Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<SessionDataStore> sessionDataStoreProvider) {
    this.settingsDataStoreProvider = settingsDataStoreProvider;
    this.sessionDataStoreProvider = sessionDataStoreProvider;
  }

  @Override
  public SettingsRepository get() {
    return newInstance(settingsDataStoreProvider.get(), sessionDataStoreProvider.get());
  }

  public static SettingsRepository_Factory create(
      Provider<SettingsDataStore> settingsDataStoreProvider,
      Provider<SessionDataStore> sessionDataStoreProvider) {
    return new SettingsRepository_Factory(settingsDataStoreProvider, sessionDataStoreProvider);
  }

  public static SettingsRepository newInstance(SettingsDataStore settingsDataStore,
      SessionDataStore sessionDataStore) {
    return new SettingsRepository(settingsDataStore, sessionDataStore);
  }
}
