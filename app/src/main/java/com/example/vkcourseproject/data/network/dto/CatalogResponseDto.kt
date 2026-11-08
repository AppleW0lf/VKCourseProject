package com.example.vkcourseproject.data.network.dto

import com.google.gson.annotations.SerializedName

data class CatalogResponseDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("developer") val developer: String?,
    @SerializedName("age_restriction") val ageRestriction: String?,
    @SerializedName("size") val size: String?,
    @SerializedName("full_description") val fullDescription: String?
)

data class AppDetailsResponseDto(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String?,
    @SerializedName("category") val category: String?,
    @SerializedName("icon") val icon: String?,
    @SerializedName("developer") val developer: String?,
    @SerializedName("age_restriction") val ageRestriction: String?,
    @SerializedName("size") val size: String?,
    @SerializedName("full_description") val fullDescription: String?
)