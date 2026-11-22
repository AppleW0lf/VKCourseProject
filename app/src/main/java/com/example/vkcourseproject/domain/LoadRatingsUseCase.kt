package com.example.vkcourseproject.domain

class LoadRatingsUseCase(private val repository: AppRepository) {
    suspend fun sequentially(ids: List<String>): Map<String, Float> =
        repository.loadRatingsSequentially(ids)

    suspend fun parallel(ids: List<String>): Map<String, Float> =
        repository.loadRatingsInParallel(ids)
}