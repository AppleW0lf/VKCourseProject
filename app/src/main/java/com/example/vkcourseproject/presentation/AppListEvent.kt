package com.example.vkcourseproject.presentation

sealed class AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent()
}