package com.u1145h.kavitaandroid.feature.home

import androidx.lifecycle.ViewModel
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow

/**
 * Supplies the embedded web UI with its base URL and the native [KavitaBridge].
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    val bridge: KavitaBridge,
    settingsRepository: SettingsRepository,
) : ViewModel() {
    val serverUrl: StateFlow<String> = settingsRepository.serverUrl
}
