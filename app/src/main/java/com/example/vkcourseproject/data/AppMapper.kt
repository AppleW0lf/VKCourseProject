package com.example.vkcourseproject.data

import com.example.vkcourseproject.data.network.dto.AppDetailsResponseDto
import com.example.vkcourseproject.data.network.dto.CatalogResponseDto
import com.example.vkcourseproject.domain.AppItem

fun CatalogResponseDto.toDomain(): AppItem {
    return AppItem(
        id = this.id,
        name = this.name,
        description = this.description ?: "Без описания",
        category = this.category ?: "Без категории",
        icon = this.icon ?: "📱",
        developer = this.developer ?: "Неизвестен",
        ageRestriction = this.ageRestriction ?: "0+",
        size = this.size ?: "Неизвестно",
        fullDescription = this.fullDescription ?: ""
    )
}
fun AppDetailsResponseDto.toDomain(): AppItem {
    return AppItem(
        id = this.id,
        name = this.name,
        description = this.description ?: "Без описания",
        category = this.category ?: "Без категории",
        icon = this.icon ?: "📱",
        developer = this.developer ?: "Неизвестен",
        ageRestriction = this.ageRestriction ?: "0+",
        size = this.size ?: "Неизвестно",
        fullDescription = this.fullDescription ?: ""
    )
}