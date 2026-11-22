package com.example.vkcourseproject

import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository

class FakeAppRepository : AppRepository {
    var appsResult: List<AppItem> = emptyList()
    var showcaseResult: List<AppItem> = emptyList()
    var throwOnApps: Throwable? = null
    var sequentialResult: Map<String, Float> = emptyMap()
    var parallelResult: Map<String, Float> = emptyMap()

    override suspend fun getApps(): List<AppItem> {
        throwOnApps?.let { throw it }
        return appsResult
    }

    override suspend fun getShowcaseApps(): List<AppItem> = showcaseResult

    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> =
        sequentialResult

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> =
        parallelResult
}