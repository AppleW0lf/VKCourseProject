package com.example.vkcourseproject

import com.example.vkcourseproject.domain.AppItem
import com.example.vkcourseproject.domain.AppRepository
import com.example.vkcourseproject.domain.GetShowcaseAppsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetShowcaseAppsUseCaseTest {

    private lateinit var fake: FakeAppRepository
    private lateinit var useCase: GetShowcaseAppsUseCase

    @Before
    fun setUp() {
        fake = FakeAppRepository()
        useCase = GetShowcaseAppsUseCase(fake)
    }

    @Test
    fun `returns showcase list`() = runTest {
        fake.showcaseResult = listOf(AppItem("1", "A", "d", "c", "i"))
        val result = useCase()
        assertEquals(1, result.size)
        assertEquals("A", result[0].name)
    }

    @Test
    fun `returns empty when repository empty`() = runTest {
        assertTrue(useCase().isEmpty())
    }

    @Test
    fun `propagates exception`() = runTest {
        val fakeThrowing = object : AppRepository {
            override suspend fun getApps() = emptyList<AppItem>()
            override suspend fun getShowcaseApps(): List<AppItem> = throw RuntimeException("x")
            override suspend fun loadRatingsSequentially(ids: List<String>) =
                emptyMap<String, Float>()
            override suspend fun loadRatingsInParallel(ids: List<String>) =
                emptyMap<String, Float>()
        }

        assertThrows(RuntimeException::class.java) {
            runBlocking { GetShowcaseAppsUseCase(fakeThrowing)() }
        }
    }

    @Test
    fun `keeps order of showcase items`() = runTest {
        fake.showcaseResult = listOf(
            AppItem("1", "A", "d", "c", "i"),
            AppItem("2", "B", "d", "c", "i"),
            AppItem("3", "C", "d", "c", "i")
        )
        assertEquals(listOf("A", "B", "C"), useCase().map { it.name })
    }

    @Test
    fun `returns at most repository size`() = runTest {
        fake.showcaseResult = (1..5).map { AppItem("$it", "N$it", "d", "c", "i") }
        assertEquals(5, useCase().size)
    }
}