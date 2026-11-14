package com.example.vkcourseproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _state = MutableStateFlow<AppDetailsState>(AppDetailsState.Loading)
    val state: StateFlow<AppDetailsState> = _state.asStateFlow()

    private val _showcaseApps = MutableStateFlow<List<AppItem>>(emptyList())
    val showcaseApps: StateFlow<List<AppItem>> = _showcaseApps.asStateFlow()
    private var installJob: Job? = null
    fun loadData(appId: String) {
        viewModelScope.launch {
            try {
                _state.value = AppDetailsState.Loading
                val apps = repository.getApps()
                val app = apps.find { it.id == appId } ?: throw Exception("App not found")
                val showcase = repository.getShowcaseApps()
                _state.value = AppDetailsState.Content(app = app, showcaseApps = showcase)
            } catch (e: Exception) {
                _state.value = AppDetailsState.Error(e.message ?: "Unknown error")
            }
        }
    }
    fun onInstallClick() {
        val current = _state.value
        if (current !is AppDetailsState.Content) return

        if (current.installState is InstallState.Installing) {
            installJob?.cancel()
            _state.update {
                (it as AppDetailsState.Content).copy(installState = InstallState.NotInstalled)
            }
        } else {
            installJob = viewModelScope.launch {
                emulateInstall()
            }
        }
    }

    private suspend fun emulateInstall() {
        try {
            for (progress in 0..100 step 5) {
                delay(150)
                _state.update {
                    (it as AppDetailsState.Content).copy(
                        installState = InstallState.Installing(progress)
                    )
                }
            }
            _state.update {
                (it as AppDetailsState.Content).copy(installState = InstallState.Installed)
            }
        } catch (e: Exception) {
        }
    }
}