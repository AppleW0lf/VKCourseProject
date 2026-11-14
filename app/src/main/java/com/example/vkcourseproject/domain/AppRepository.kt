package com.example.vkcourseproject.domain

interface AppRepository {
    suspend fun getApps(): List<AppItem>
    suspend fun getShowcaseApps(): List<AppItem>
    suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float>
    suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float>
}