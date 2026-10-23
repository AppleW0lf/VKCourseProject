package com.example.vkcourseproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AppListViewModel : ViewModel() {
    private val repository = AppDetailsRepository()

    private val _uiState = MutableStateFlow(AppListState())
    val uiState: StateFlow<AppListState> = _uiState.asStateFlow()

    private val _events = Channel<AppListEvent>(BUFFERED)
    val events = _events.receiveAsFlow()

    init {
        loadApps()
    }

    private fun loadApps() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val apps = repository.getApps()

            _uiState.value = _uiState.value.copy(
                apps = apps,
                isLoading = false
            )
        }
    }

    fun onToolbarClicked() {
        viewModelScope.launch {
            _events.send(AppListEvent.ShowSnackbar("Нажата кнопка меню!"))
        }
    }
}