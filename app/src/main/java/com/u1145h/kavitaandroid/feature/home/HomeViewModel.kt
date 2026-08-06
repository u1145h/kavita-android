package com.u1145h.kavitaandroid.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Supplies the embedded web UI with its base URL and the native [KavitaBridge].
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    val bridge: KavitaBridge,
    private val settingsRepository: SettingsRepository,
) : ViewModel() {
    val serverUrl: StateFlow<String> = settingsRepository.serverUrl

    fun setServerUrl(url: String) {
        viewModelScope.launch { settingsRepository.setServerUrl(url) }
    }
}
