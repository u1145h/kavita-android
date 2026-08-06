package com.u1145h.kavitaandroid.di;

import com.u1145h.kavitaandroid.data.remote.api.KavitaApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideKavitaApiServiceFactory implements Factory<KavitaApiService> {
  private final Provider<Retrofit> retrofitProvider;

  private NetworkModule_ProvideKavitaApiServiceFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public KavitaApiService get() {
    return provideKavitaApiService(retrofitProvider.get());
  }

  public static NetworkModule_ProvideKavitaApiServiceFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideKavitaApiServiceFactory(retrofitProvider);
  }

  public static KavitaApiService provideKavitaApiService(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideKavitaApiService(retrofit));
  }
}
