package com.example.vkcourseproject

import com.example.vkcourseproject.data.network.dto.AppDetailsResponseDto
import com.example.vkcourseproject.data.network.dto.CatalogResponseDto
import com.example.vkcourseproject.data.toDomain
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AppMapperTest {

    @Test
    fun `catalog toDomain maps all fields correctly`() {
        val dto = CatalogResponseDto(
            id = "1",
            name = "Minecraft",
            description = "Строй, исследуй и выживай",
            category = "Песочница",
            icon = "⛏️",
            developer = "Mojang",
            ageRestriction = "6+",
            size = "800 MB",
            fullDescription = "Описание",
            rating = 4.5f
        )

        val item = dto.toDomain()

        assertEquals("1", item.id)
        assertEquals("Minecraft", item.name)
        assertEquals("Строй, исследуй и выживай", item.description)
        assertEquals("Песочница", item.category)
        assertEquals("⛏️", item.icon)
        assertEquals("Mojang", item.developer)
        assertEquals("6+", item.ageRestriction)
        assertEquals("800 MB", item.size)
        assertEquals("Описание", item.fullDescription)
        assertEquals(4.5f, item.rating)
    }

    @Test
    fun `catalog toDomain uses defaults when nullable fields are null`() {
        val dto = CatalogResponseDto(
            id = "2",
            name = "App",
            description = null,
            category = null,
            icon = null,
            developer = null,
            ageRestriction = null,
            size = null,
            fullDescription = null,
            rating = null
        )

        val item = dto.toDomain()

        assertEquals("Без описания", item.description)
        assertEquals("Без категории", item.category)
        assertEquals("📱", item.icon)
        assertEquals("Неизвестен", item.developer)
        assertEquals("0+", item.ageRestriction)
        assertEquals("Неизвестно", item.size)
        assertEquals("", item.fullDescription)
        assertNull(item.rating)
    }

    @Test
    fun `catalog toDomain preserves rating null`() {
        val dto = CatalogResponseDto(
            id = "3", name = "App", description = "d", category = "c",
            icon = "i", developer = "dev", ageRestriction = "0+",
            size = "1 MB", fullDescription = "f", rating = null
        )
        assertNull(dto.toDomain().rating)
    }


    @Test
    fun `details toDomain maps all fields correctly`() {
        val dto = AppDetailsResponseDto(
            id = "10",
            name = "Genshin Impact",
            description = "Открытый мир",
            category = "RPG",
            icon = "⚔️",
            developer = "HoYoverse",
            ageRestriction = "12+",
            size = "3 GB",
            fullDescription = "Эпическая RPG",
            rating = 4.8f
        )

        val item = dto.toDomain()

        assertEquals("10", item.id)
        assertEquals("Genshin Impact", item.name)
        assertEquals("Открытый мир", item.description)
        assertEquals("RPG", item.category)
        assertEquals("⚔️", item.icon)
        assertEquals("HoYoverse", item.developer)
        assertEquals("12+", item.ageRestriction)
        assertEquals("3 GB", item.size)
        assertEquals("Эпическая RPG", item.fullDescription)
        assertEquals(4.8f, item.rating)
    }

    @Test
    fun `details toDomain uses fallback values for nulls`() {
        val dto = AppDetailsResponseDto(
            id = "11", name = "App", description = null, category = null,
            icon = null, developer = null, ageRestriction = null,
            size = null, fullDescription = null, rating = null
        )

        val item = dto.toDomain()

        assertEquals("Без описания", item.description)
        assertEquals("Без категории", item.category)
        assertEquals("📱", item.icon)
        assertEquals("Неизвестен", item.developer)
        assertEquals("0+", item.ageRestriction)
        assertEquals("Неизвестно", item.size)
        assertEquals("", item.fullDescription)
        assertNull(item.rating)
    }

    @Test
    fun `details toDomain id is always non-null string`() {
        val dto = AppDetailsResponseDto(
            id = "42", name = "N", description = "d", category = "c",
            icon = "i", developer = "dev", ageRestriction = "0+",
            size = "s", fullDescription = "f", rating = 1f
        )
        assertEquals("42", dto.toDomain().id)
    }
}