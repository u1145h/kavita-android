package com.u1145h.kavitaandroid.data.remote.auth

import com.u1145h.kavitaandroid.data.local.datastore.Session
import com.u1145h.kavitaandroid.data.local.datastore.SessionDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Holds the current authentication session in memory (mirrored from
 * [SessionDataStore]) so interceptors and readers can access the token cheaply.
 */
@Singleton
class SessionManager @Inject constructor(
    private val sessionDataStore: SessionDataStore,
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _session = MutableStateFlow(Session())
    val session: StateFlow<Session> = _session

    val isLoggedIn: StateFlow<Boolean> = _session
        .map { it.isLoggedIn }
        .stateIn(scope, kotlinx.coroutines.flow.SharingStarted.Eagerly, false)

    init {
        scope.launch {
            sessionDataStore.session.collect { _session.value = it }
        }
    }

    val token: String? get() = _session.value.token
    val apiKey: String? get() = _session.value.apiKey

    suspend fun update(session: Session) {
        sessionDataStore.update(session)
    }

    suspend fun clear() {
        sessionDataStore.clear()
    }
}
