package com.example.vkcourseproject.data

import android.os.SystemClock
import com.example.vkcourseproject.data.network.AppApi
import com.example.vkcourseproject.data.network.dto.AppDetailsResponseDto
import com.example.vkcourseproject.data.network.dto.CatalogResponseDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AppRepositoryImplTest {

    private lateinit var api: AppApi
    private lateinit var repository: AppRepositoryImpl

    private fun catalogDto(id: String, name: String = "App$id") = CatalogResponseDto(
        id = id, name = name, description = "desc$id", category = "cat",
        icon = "icon", developer = "dev", ageRestriction = "6+",
        size = "100 MB", fullDescription = "full$id", rating = 4f
    )

    private fun detailsDto(id: String) = AppDetailsResponseDto(
        id = id, name = "App$id", description = "d", category = "c",
        icon = "i", developer = "dev", ageRestriction = "6+",
        size = "1 MB", fullDescription = "f", rating = 5f
    )

    @Before
    fun setUp() {
        api = mockk()
        repository = AppRepositoryImpl(api)

        mockkStatic(SystemClock::class)
        every { SystemClock.elapsedRealtime() } returns 100L
    }

    @After
    fun tearDown() {
        unmockkStatic(SystemClock::class)
    }

    @Test
    fun `getApps returns mapped domain items`() = runTest {
        coEvery { api.getCatalog() } returns listOf(catalogDto("1"), catalogDto("2"))

        val result = repository.getApps()

        assertEquals(2, result.size)
        assertEquals("1", result[0].id)
        assertEquals("App2", result[1].name)
    }

    @Test
    fun `getApps returns empty list`() = runTest {
        coEvery { api.getCatalog() } returns emptyList()
        assertTrue(repository.getApps().isEmpty())
    }

    @Test
    fun `getApps propagates error`() = runTest {
        coEvery { api.getCatalog() } throws RuntimeException("network")

        assertThrows(RuntimeException::class.java) {
            runBlocking { repository.getApps() }
        }
    }

    @Test
    fun `getShowcaseApps limits to 9`() = runTest {
        coEvery { api.getCatalog() } returns (1..20).map { catalogDto("$it") }

        val result = repository.getShowcaseApps()

        assertEquals(9, result.size)
        assertEquals("1", result.first().id)
        assertEquals("9", result.last().id)
    }

    @Test
    fun `getShowcaseApps returns all when fewer than 9`() = runTest {
        coEvery { api.getCatalog() } returns listOf(catalogDto("1"), catalogDto("2"))

        assertEquals(2, repository.getShowcaseApps().size)
    }

    @Test
    fun `getShowcaseApps empty`() = runTest {
        coEvery { api.getCatalog() } returns emptyList()
        assertTrue(repository.getShowcaseApps().isEmpty())
    }

    @Test
    fun `getAppDetails maps dto`() = runTest {
        coEvery { api.getAppDetails(5) } returns detailsDto("5")

        val item = repository.getAppDetails(5)

        assertEquals("5", item.id)
        assertEquals("App5", item.name)
        coVerify { api.getAppDetails(5) }
    }

    @Test
    fun `getAppDetails propagates error`() = runTest {
        coEvery { api.getAppDetails(99) } throws IllegalStateException("not found")

        assertThrows(IllegalStateException::class.java) {
            runBlocking { repository.getAppDetails(99) }
        }
    }

    @Test
    fun `loadRatingsSequentially returns map`() = runTest {
        coEvery { api.getRating("1") } returns 4.1f
        coEvery { api.getRating("2") } returns 3.7f

        val result = repository.loadRatingsSequentially(listOf("1", "2"))

        assertEquals(2, result.size)
        assertEquals(4.1f, result["1"])
        assertEquals(3.7f, result["2"])
    }

    @Test
    fun `loadRatingsSequentially empty`() = runTest {
        assertTrue(repository.loadRatingsSequentially(emptyList()).isEmpty())
    }

    @Test
    fun `loadRatingsSequentially propagates error`() = runTest {
        coEvery { api.getRating(any()) } throws RuntimeException("fail")

        assertThrows(RuntimeException::class.java) {
            runBlocking { repository.loadRatingsSequentially(listOf("1")) }
        }
    }

    @Test
    fun `loadRatingsInParallel returns map`() = runTest {
        coEvery { api.getRating("1") } returns 4.2f
        coEvery { api.getRating("2") } returns 3.9f
        coEvery { api.getRating("3") } returns 5f

        val result = repository.loadRatingsInParallel(listOf("1", "2", "3"))

        assertEquals(3, result.size)
        assertEquals(4.2f, result["1"])
        assertEquals(3.9f, result["2"])
        assertEquals(5f, result["3"])
    }

    @Test
    fun `loadRatingsInParallel empty`() = runTest {
        assertTrue(repository.loadRatingsInParallel(emptyList()).isEmpty())
    }

    @Test
    fun `loadRatingsInParallel calls api once per id`() = runTest {
        coEvery { api.getRating(any()) } returns 4f

        repository.loadRatingsInParallel(listOf("1", "2", "3", "4", "5"))

        coVerify(exactly = 1) { api.getRating("1") }
        coVerify(exactly = 1) { api.getRating("5") }
    }
}