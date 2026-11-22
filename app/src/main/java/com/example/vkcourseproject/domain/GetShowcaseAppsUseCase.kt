package com.example.vkcourseproject.domain

class GetShowcaseAppsUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(): List<AppItem> = repository.getShowcaseApps()
}