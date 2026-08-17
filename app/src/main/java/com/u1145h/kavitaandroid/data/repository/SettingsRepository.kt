package com.u1145h.kavitaandroid.data.repository

import com.u1145h.kavitaandroid.data.local.datastore.AppSettings
import com.u1145h.kavitaandroid.data.local.datastore.Session
import com.u1145h.kavitaandroid.data.local.datastore.SessionDataStore
import com.u1145h.kavitaandroid.data.local.datastore.SettingsDataStore
import com.u1145h.kavitaandroid.domain.model.ThemeMode
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Single entry point for app preferences and the persisted session.
 */
@Singleton
class SettingsRepository @Inject constructor(
    private val settingsDataStore: SettingsDataStore,
    private val sessionDataStore: SessionDataStore,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    val settings: Flow<AppSettings> = settingsDataStore.settings

    val serverUrl: Flow<String> = settings
        .map { it.serverUrl }
        .distinctUntilChanged()

    @Volatile
    var currentServerUrl: String = ""
        private set

    val session: Flow<Session> = sessionDataStore.session

    init {
        scope.launch {
            serverUrl.collect { currentServerUrl = it }
        }
    }

    suspend fun setServerUrl(url: String) = settingsDataStore.setServerUrl(url)

    suspend fun setThemeMode(mode: ThemeMode) = settingsDataStore.setThemeMode(mode)

    suspend fun setDynamicColor(enabled: Boolean) = settingsDataStore.setDynamicColor(enabled)

    suspend fun setDeveloperMode(enabled: Boolean) = settingsDataStore.setDeveloperMode(enabled)

    suspend fun setVerboseLogging(enabled: Boolean) = settingsDataStore.setVerboseLogging(enabled)

    suspend fun setLastSyncAt(utcMillis: Long) = settingsDataStore.setLastSyncAt(utcMillis)

    suspend fun updateSession(session: Session) = sessionDataStore.update(session)

    suspend fun clearSession() = sessionDataStore.clear()
}
