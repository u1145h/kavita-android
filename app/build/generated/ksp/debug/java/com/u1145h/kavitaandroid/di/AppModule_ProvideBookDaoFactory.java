package com.u1145h.kavitaandroid.di;

import com.u1145h.kavitaandroid.data.local.db.KavitaDatabase;
import com.u1145h.kavitaandroid.data.local.db.dao.BookDao;
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
public final class AppModule_ProvideBookDaoFactory implements Factory<BookDao> {
  private final Provider<KavitaDatabase> dbProvider;

  private AppModule_ProvideBookDaoFactory(Provider<KavitaDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public BookDao get() {
    return provideBookDao(dbProvider.get());
  }

  public static AppModule_ProvideBookDaoFactory create(Provider<KavitaDatabase> dbProvider) {
    return new AppModule_ProvideBookDaoFactory(dbProvider);
  }

  public static BookDao provideBookDao(KavitaDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideBookDao(db));
  }
}
