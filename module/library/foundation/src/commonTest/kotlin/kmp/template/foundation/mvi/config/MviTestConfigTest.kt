package kmp.template.foundation.mvi.config

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kmp.template.foundation.mvi.config.exception.MviTestExceptionHandler
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class MviTestConfigTest {

    private val testCoroutineDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val tested: MviConfig = MviTestConfig(testCoroutineDispatcher)

    @Test
    fun `verify test coroutines exception handler`() {
        assertEquals(MviTestExceptionHandler::class, tested.exceptionHandler::class)
    }

    @Test
    fun `verify test launch dispatcher`() {
        assertEquals(testCoroutineDispatcher, tested.launchDispatcher)
    }
}
