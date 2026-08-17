package com.u1145h.kavitaandroid.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1145h.kavitaandroid.data.remote.ServerHealthChecker
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 * First-run server setup status.
 */
sealed interface SetupState {
    data object Idle : SetupState
    data object Checking : SetupState
    data class Error(val message: String) : SetupState
    data object Connected : SetupState
}

/**
 * Represents the server URL state when the app launches.
 */
sealed interface ServerUrlState {
    data object Loading : ServerUrlState
    data object Unconfigured : ServerUrlState
    data class Configured(val url: String) : ServerUrlState
}

/**
 * Supplies the embedded web UI with its base URL and the native [KavitaBridge].
 * On first run (no saved server URL) drives the setup flow that verifies the
 * address before it is persisted.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    val bridge: KavitaBridge,
    private val settingsRepository: SettingsRepository,
    private val healthChecker: ServerHealthChecker,
) : ViewModel() {
    val serverUrlState: StateFlow<ServerUrlState> = settingsRepository.serverUrl
        .map { url ->
            if (url.isBlank()) {
                ServerUrlState.Unconfigured
            } else {
                ServerUrlState.Configured(url)
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.Eagerly,
            initialValue = ServerUrlState.Loading,
        )

    private val _setupState = MutableStateFlow<SetupState>(SetupState.Idle)
    val setupState: StateFlow<SetupState> = _setupState.asStateFlow()

    fun submitServerUrl(raw: String) {
        if (_setupState.value == SetupState.Checking) return
        val normalized = normalizeUrl(raw)
        if (normalized == null) {
            _setupState.value = SetupState.Error("That doesn't look like a valid server address.")
            return
        }
        viewModelScope.launch {
            _setupState.value = SetupState.Checking
            healthChecker.check(normalized)
                .onSuccess {
                    settingsRepository.setServerUrl(normalized)
                    _setupState.value = SetupState.Connected
                }
                .onFailure {
                    _setupState.value = SetupState.Error("Couldn't reach that server. Check the address and try again.")
                }
        }
    }

    private fun normalizeUrl(raw: String): String? {
        val trimmed = raw.trim().removeSuffix("/")
        if (trimmed.isEmpty()) return null
        val withScheme = if (trimmed.contains("://")) trimmed else "http://$trimmed"
        val parsed = withScheme.toHttpUrlOrNull() ?: return null
        if (parsed.host.isBlank()) return null
        return parsed.toString().removeSuffix("/")
    }
}
