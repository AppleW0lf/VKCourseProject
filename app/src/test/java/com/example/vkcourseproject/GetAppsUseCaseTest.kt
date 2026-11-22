package com.example.vkcourseproject

import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.GetAppsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetAppsUseCaseTest {

    private lateinit var fake: FakeAppRepository
    private lateinit var useCase: GetAppsUseCase

    @Before
    fun setUp() {
        fake = FakeAppRepository()
        useCase = GetAppsUseCase(fake)
    }

    @Test
    fun `returns list from repository`() = runTest {
        fake.appsResult = listOf(
            AppItem("1", "A", "d", "c", "i"),
            AppItem("2", "B", "d", "c", "i")
        )

        val result = useCase()

        assertEquals(2, result.size)
        assertEquals("A", result[0].name)
    }

    @Test
    fun `returns empty list when repository empty`() = runTest {
        fake.appsResult = emptyList()
        assertTrue(useCase().isEmpty())
    }

    @Test
    fun `propagates repository exception`() = runTest {
        fake.throwOnApps = IllegalStateException("boom")

        assertThrows(IllegalStateException::class.java) {
            runBlocking { useCase() }
        }
    }

    @Test
    fun `preserves order of items`() = runTest {
        fake.appsResult = listOf(
            AppItem("3", "C", "d", "c", "i"),
            AppItem("1", "A", "d", "c", "i"),
            AppItem("2", "B", "d", "c", "i")
        )
        val result = useCase()
        assertEquals(listOf("C", "A", "B"), result.map { it.name })
    }

    @Test
    fun `returns exactly what repository gives`() = runTest {
        val expected = AppItem("9", "X", "d", "c", "i", rating = 4.9f)
        fake.appsResult = listOf(expected)
        assertEquals(listOf(expected), useCase())
    }
}