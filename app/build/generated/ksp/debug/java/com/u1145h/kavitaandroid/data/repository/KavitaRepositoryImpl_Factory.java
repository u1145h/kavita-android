package com.u1145h.kavitaandroid.data.repository;

import com.u1145h.kavitaandroid.data.remote.api.KavitaApiService;
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
public final class KavitaRepositoryImpl_Factory implements Factory<KavitaRepositoryImpl> {
  private final Provider<KavitaApiService> apiProvider;

  private KavitaRepositoryImpl_Factory(Provider<KavitaApiService> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public KavitaRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static KavitaRepositoryImpl_Factory create(Provider<KavitaApiService> apiProvider) {
    return new KavitaRepositoryImpl_Factory(apiProvider);
  }

  public static KavitaRepositoryImpl newInstance(KavitaApiService api) {
    return new KavitaRepositoryImpl(api);
  }
}
