package com.u1145h.kavitaandroid.di;

import com.u1145h.kavitaandroid.data.local.db.KavitaDatabase;
import com.u1145h.kavitaandroid.data.local.db.dao.DownloadQueueDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideDownloadQueueDaoFactory implements Factory<DownloadQueueDao> {
  private final Provider<KavitaDatabase> dbProvider;

  private AppModule_ProvideDownloadQueueDaoFactory(Provider<KavitaDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DownloadQueueDao get() {
    return provideDownloadQueueDao(dbProvider.get());
  }

  public static AppModule_ProvideDownloadQueueDaoFactory create(
      Provider<KavitaDatabase> dbProvider) {
    return new AppModule_ProvideDownloadQueueDaoFactory(dbProvider);
  }

  public static DownloadQueueDao provideDownloadQueueDao(KavitaDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDownloadQueueDao(db));
  }
}
