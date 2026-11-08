package com.example.vkcourseproject.data

import com.example.vkcourseproject.data.network.AppApi
import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository
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
}