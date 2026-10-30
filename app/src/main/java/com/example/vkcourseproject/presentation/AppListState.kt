package com.example.vkcourseproject.presentation

import com.example.vkcourseproject.domain.AppItem

data class AppListState(
    val apps: List<AppItem> = emptyList(),
    val isLoading: Boolean = false
)