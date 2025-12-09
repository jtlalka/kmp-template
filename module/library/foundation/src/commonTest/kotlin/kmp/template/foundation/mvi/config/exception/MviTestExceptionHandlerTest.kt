package kmp.template.foundation.mvi.config.exception

import kotlin.coroutines.EmptyCoroutineContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class MviTestExceptionHandlerTest {

    private val tested = MviTestExceptionHandler()

    @Test
    fun `rethrows provided exception when handleException is called`() {
        val exception = IllegalStateException("expected")

        val thrown = assertFailsWith<IllegalStateException> {
            tested.handleException(EmptyCoroutineContext, exception)
        }

        assertEquals(exception, thrown)
    }
}
