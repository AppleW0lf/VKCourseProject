package com.example.vkcourseproject.data

import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository

class AppRepositoryImpl : AppRepository {
    override suspend fun getApps(): List<AppItem> {
        return mockDatabase.map { it.toDomain() }
    }

    override suspend fun getShowcaseApps(): List<AppItem> {
        return mockDatabase.take(9).map { it.toDomain() }
    }
}