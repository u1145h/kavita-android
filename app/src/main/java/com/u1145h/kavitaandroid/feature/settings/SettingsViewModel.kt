package com.u1145h.kavitaandroid.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.u1145h.kavitaandroid.data.local.datastore.AppSettings
import com.u1145h.kavitaandroid.data.local.db.dao.StorageStats
import com.u1145h.kavitaandroid.data.remote.auth.SessionManager
import com.u1145h.kavitaandroid.data.repository.BookRepository
import com.u1145h.kavitaandroid.data.repository.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val sessionManager: SessionManager,
    private val bookRepository: BookRepository,
) : ViewModel() {

    val settings: StateFlow<AppSettings> = settingsRepository.settings
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), AppSettings())

    val isLoggedIn: StateFlow<Boolean> = sessionManager.isLoggedIn

    val storageStats: StateFlow<StorageStats> = bookRepository.observeStats()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), StorageStats(0, 0))

    fun setServerUrl(url: String) = viewModelScope.launch {
        settingsRepository.setServerUrl(url)
    }

    fun setDynamicColor(enabled: Boolean) = viewModelScope.launch {
        settingsRepository.setDynamicColor(enabled)
    }

    fun setDeveloperMode(enabled: Boolean) = viewModelScope.launch {
        settingsRepository.setDeveloperMode(enabled)
    }

    fun setVerboseLogging(enabled: Boolean) = viewModelScope.launch {
        settingsRepository.setVerboseLogging(enabled)
    }

    fun clearLibrary() = viewModelScope.launch {
        bookRepository.clearAll()
    }

    fun logout() = viewModelScope.launch {
        sessionManager.clear()
    }
}
