package kmp.template.foundation.mvi.config.exception

import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test

class MviExceptionHandlerTest {

    private val tested = MviExceptionHandler()

    @Test
    fun `does not throw provided exception when handleException is called`() {
        val exception = IllegalArgumentException("boom")

        tested.handleException(
            context = EmptyCoroutineContext,
            exception = exception
        )
    }
}
