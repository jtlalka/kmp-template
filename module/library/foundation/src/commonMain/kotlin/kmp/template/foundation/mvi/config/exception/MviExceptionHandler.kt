package kmp.template.foundation.mvi.config.exception

import co.touchlab.kermit.Logger
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Fallback MVI (Redux) exception handler.
 *
 * This exception handler is called for exceptions which are not handled directly in MVI Intents.
 */
internal class MviExceptionHandler :
    AbstractCoroutineContextElement(CoroutineExceptionHandler),
    CoroutineExceptionHandler {

    override fun handleException(
        context: CoroutineContext,
        exception: Throwable
    ) = Logger.e(exception) { "MVI exception handler" }
}
