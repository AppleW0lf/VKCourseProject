package com.example.vkcourseproject.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppDetailsViewModel @Inject constructor(
    private val repository: AppRepository
) : ViewModel() {

    private val _app = MutableStateFlow<AppItem?>(null)
    val app: StateFlow<AppItem?> = _app.asStateFlow()

    private val _showcaseApps = MutableStateFlow<List<AppItem>>(emptyList())
    val showcaseApps: StateFlow<List<AppItem>> = _showcaseApps.asStateFlow()

    fun loadData(appId: Int) {
        viewModelScope.launch {
            val allApps = repository.getApps()
            _app.value = allApps.find { it.id == appId } ?: allApps.firstOrNull()
            _showcaseApps.value = repository.getShowcaseApps()
        }
    }
}