package com.u1145h.kavitaandroid.data.remote.auth;

import com.u1145h.kavitaandroid.data.local.datastore.SessionDataStore;
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
public final class SessionManager_Factory implements Factory<SessionManager> {
  private final Provider<SessionDataStore> sessionDataStoreProvider;

  private SessionManager_Factory(Provider<SessionDataStore> sessionDataStoreProvider) {
    this.sessionDataStoreProvider = sessionDataStoreProvider;
  }

  @Override
  public SessionManager get() {
    return newInstance(sessionDataStoreProvider.get());
  }

  public static SessionManager_Factory create(Provider<SessionDataStore> sessionDataStoreProvider) {
    return new SessionManager_Factory(sessionDataStoreProvider);
  }

  public static SessionManager newInstance(SessionDataStore sessionDataStore) {
    return new SessionManager(sessionDataStore);
  }
}
