package com.example.vkcourseproject

import com.example.vkcourseproject.domain.LoadRatingsUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadRatingsUseCaseTest {

    private lateinit var fake: FakeAppRepository
    private lateinit var useCase: LoadRatingsUseCase

    @Before
    fun setUp() {
        fake = FakeAppRepository()
        useCase = LoadRatingsUseCase(fake)
    }

    @Test
    fun `sequentially returns repository map`() = runTest {
        fake.sequentialResult = mapOf("1" to 4.5f, "2" to 3.2f)
        val result = useCase.sequentially(listOf("1", "2"))
        assertEquals(2, result.size)
        assertEquals(4.5f, result["1"]!!, 0.0001f)
    }

    @Test
    fun `parallel returns repository map`() = runTest {
        fake.parallelResult = mapOf("1" to 4.5f, "2" to 3.2f)
        val result = useCase.parallel(listOf("1", "2"))
        assertEquals(3.2f, result["2"]!!, 0.0001f)
    }

    @Test
    fun `sequentially with empty ids returns empty`() = runTest {
        assertTrue(useCase.sequentially(emptyList()).isEmpty())
    }

    @Test
    fun `parallel with empty ids returns empty`() = runTest {
        assertTrue(useCase.parallel(emptyList()).isEmpty())
    }

    @Test
    fun `sequentially and parallel return equal maps for same data`() = runTest {
        val map = mapOf("a" to 1f, "b" to 2f)
        fake.sequentialResult = map
        fake.parallelResult = map
        assertEquals(
            useCase.sequentially(listOf("a", "b")),
            useCase.parallel(listOf("a", "b"))
        )
    }
}