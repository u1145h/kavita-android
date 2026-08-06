package com.u1145h.kavitaandroid.data.local.files;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class BookFileManager_Factory implements Factory<BookFileManager> {
  private final Provider<Context> contextProvider;

  private BookFileManager_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public BookFileManager get() {
    return newInstance(contextProvider.get());
  }

  public static BookFileManager_Factory create(Provider<Context> contextProvider) {
    return new BookFileManager_Factory(contextProvider);
  }

  public static BookFileManager newInstance(Context context) {
    return new BookFileManager(context);
  }
}
