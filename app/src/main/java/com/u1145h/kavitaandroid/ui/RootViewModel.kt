package com.u1145h.kavitaandroid.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1145h.kavitaandroid.data.local.datastore.AppSettings
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

import kotlinx.coroutines.flow.map

/**
 * App-level settings (theme, dynamic color) observed by the root composable so
 * the whole application re-themes reactively.
 */
@HiltViewModel
class RootViewModel @Inject constructor(
    settingsRepository: SettingsRepository,
) : ViewModel() {
    private val settingsState: StateFlow<AppSettings?> = settingsRepository.settings
        .map<AppSettings, AppSettings?> { it }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    val settings: StateFlow<AppSettings> = settingsRepository.settings
        .stateIn(viewModelScope, SharingStarted.Eagerly, AppSettings())

    val isReady: StateFlow<Boolean> = settingsState
        .map { it != null }
        .stateIn(viewModelScope, SharingStarted.Eagerly, false)
}
