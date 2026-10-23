package com.example.vkcourseproject

data class AppListState(
    val apps: List<AppItem> = emptyList(),
    val isLoading: Boolean = false
)