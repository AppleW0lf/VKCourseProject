package com.example.vkcourseproject.data

import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    override suspend fun getApps(): List<AppItem> {
        return mockDatabase.map { it.toDomain() }
    }

    override suspend fun getShowcaseApps(): List<AppItem> {
        return mockDatabase.take(9).map { it.toDomain() }
    }
}