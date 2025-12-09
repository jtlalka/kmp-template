package kmp.template.foundation.mvi.config

import kmp.template.foundation.mvi.config.dispatcher.MviDispatcher
import kmp.template.foundation.mvi.config.exception.MviExceptionHandler
import kotlin.test.Test
import kotlin.test.assertEquals

class MviDefaultConfigTest {

    private val tested: MviConfig = MviDefaultConfig()

    @Test
    fun `verify default coroutines exception handler`() {
        assertEquals(MviExceptionHandler::class, tested.exceptionHandler::class)
    }

    @Test
    fun `verify default launch dispatcher`() {
        assertEquals(MviDispatcher.main, tested.launchDispatcher)
    }
}
