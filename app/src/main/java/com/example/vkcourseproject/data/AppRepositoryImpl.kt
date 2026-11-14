package com.example.vkcourseproject.data

import android.os.SystemClock
import android.util.Log
import com.example.vkcourseproject.data.network.AppApi
import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor( private val api: AppApi) : AppRepository {
    override suspend fun getApps(): List<AppItem> {
        return api.getCatalog().map { it.toDomain() }
    }

    override suspend fun getShowcaseApps(): List<AppItem> {
        return api.getCatalog().take(9).map { it.toDomain() }
    }

    suspend fun getAppDetails(id: Int): AppItem {
        return api.getAppDetails(id).toDomain()
    }
    override suspend fun loadRatingsSequentially(ids: List<String>): Map<String, Float> {
        val startTime = SystemClock.elapsedRealtime()
        val ratings = mutableMapOf<String, Float>()

        for (id in ids) {
            val rating = api.getRating(id)
            ratings[id] = rating
        }

        val endTime = SystemClock.elapsedRealtime()
        Log.d("RatingTest", "Sequential time: ${endTime - startTime} ms")
        return ratings
    }

    override suspend fun loadRatingsInParallel(ids: List<String>): Map<String, Float> = coroutineScope {
        val startTime = SystemClock.elapsedRealtime()

        val ratingsList = ids.map { id ->
            async { id to api.getRating(id) }
        }.awaitAll()

        val endTime = SystemClock.elapsedRealtime()
        Log.d("RatingTest", "Parallel time: ${endTime - startTime} ms")

        ratingsList.toMap()
    }
}