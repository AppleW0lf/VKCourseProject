package com.example.vkcourseproject.data.network

import com.example.vkcourseproject.data.network.dto.AppDetailsResponseDto
import com.example.vkcourseproject.data.network.dto.CatalogResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {
    @GET("catalog")
    suspend fun getCatalog(): List<CatalogResponseDto>

    @GET("{id}")
    suspend fun getAppDetails(@Path("id") id: Int): AppDetailsResponseDto
}