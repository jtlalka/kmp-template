package kmp.template.foundation.mvi.config.exception

import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Test MVI (Redux) exception handler - always throws an exception.
 */
internal class MviTestExceptionHandler :
    AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    override fun handleException(
        context: CoroutineContext,
        exception: Throwable
    ) = throw exception
}
