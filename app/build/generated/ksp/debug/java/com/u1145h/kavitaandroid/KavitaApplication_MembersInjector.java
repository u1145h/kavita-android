package com.u1145h.kavitaandroid;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class KavitaApplication_MembersInjector implements MembersInjector<KavitaApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  private KavitaApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  @Override
  public void injectMembers(KavitaApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  public static MembersInjector<KavitaApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new KavitaApplication_MembersInjector(workerFactoryProvider);
  }

  @InjectedFieldSignature("com.u1145h.kavitaandroid.KavitaApplication.workerFactory")
  public static void injectWorkerFactory(KavitaApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
