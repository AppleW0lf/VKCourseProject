package com.example.vkcourseproject

sealed class AppListEvent {
    data class ShowSnackbar(val message: String) : AppListEvent()
}