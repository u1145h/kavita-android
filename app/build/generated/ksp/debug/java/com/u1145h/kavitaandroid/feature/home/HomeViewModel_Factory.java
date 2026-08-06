package com.u1145h.kavitaandroid.feature.home;

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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<KavitaBridge> bridgeProvider;

  private final Provider<SettingsRepository> settingsRepositoryProvider;

  private HomeViewModel_Factory(Provider<KavitaBridge> bridgeProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    this.bridgeProvider = bridgeProvider;
    this.settingsRepositoryProvider = settingsRepositoryProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(bridgeProvider.get(), settingsRepositoryProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<KavitaBridge> bridgeProvider,
      Provider<SettingsRepository> settingsRepositoryProvider) {
    return new HomeViewModel_Factory(bridgeProvider, settingsRepositoryProvider);
  }

  public static HomeViewModel newInstance(KavitaBridge bridge,
      SettingsRepository settingsRepository) {
    return new HomeViewModel(bridge, settingsRepository);
  }
}
