package com.u1145h.kavitaandroid.di;

import com.u1145h.kavitaandroid.data.local.db.KavitaDatabase;
import com.u1145h.kavitaandroid.data.local.db.dao.ReadingSessionDao;
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
public final class AppModule_ProvideReadingSessionDaoFactory implements Factory<ReadingSessionDao> {
  private final Provider<KavitaDatabase> dbProvider;

  private AppModule_ProvideReadingSessionDaoFactory(Provider<KavitaDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ReadingSessionDao get() {
    return provideReadingSessionDao(dbProvider.get());
  }

  public static AppModule_ProvideReadingSessionDaoFactory create(
      Provider<KavitaDatabase> dbProvider) {
    return new AppModule_ProvideReadingSessionDaoFactory(dbProvider);
  }

  public static ReadingSessionDao provideReadingSessionDao(KavitaDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideReadingSessionDao(db));
  }
}
