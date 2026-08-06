package com.u1145h.kavitaandroid.ui;

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
public final class RootViewModel_Factory implements Factory<RootViewModel> {
  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private RootViewModel_Factory(Provider<SettingsRepository> settingsRepositoryProvider) {
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public RootViewModel get() {
    return newInstance(settingsRepositoryProvider.get());
  }

  public static RootViewModel_Factory create(
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new RootViewModel_Factory(settingsRepositoryProvider);
  }

  public static RootViewModel newInstance(SettingsRepository settingsRepository) {
    return new RootViewModel(settingsRepository);
  }
}
