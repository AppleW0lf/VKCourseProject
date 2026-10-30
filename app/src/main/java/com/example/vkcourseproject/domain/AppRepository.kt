package com.example.vkcourseproject.domain

interface AppRepository {
    suspend fun getApps(): List<AppItem>
    suspend fun getShowcaseApps(): List<AppItem>
}