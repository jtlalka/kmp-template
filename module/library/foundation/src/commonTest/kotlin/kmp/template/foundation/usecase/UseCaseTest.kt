package kmp.template.foundation.usecase

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

class UseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun `trigger invoke operator with input when use case is called`() = runTest(dispatcher) {
        val givenUseCase = EchoUseCase()

        val result = givenUseCase("World")

        assertEquals("Hello World", result)
    }

    private class EchoUseCase : UseCase<String, String> {
        override suspend fun invoke(input: String): String = "Hello $input"
    }
}
