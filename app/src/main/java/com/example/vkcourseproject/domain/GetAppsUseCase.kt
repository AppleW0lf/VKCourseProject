package com.example.vkcourseproject.domain

class GetAppsUseCase(private val repository: AppRepository) {
    suspend operator fun invoke(): List<AppItem> = repository.getApps()
}