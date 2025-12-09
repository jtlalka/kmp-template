package kmp.template.foundation.mvi.config.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Default dispatchers used in MVI (Redux) container implementation.
 */
internal object MviDispatcher {

    val main: CoroutineDispatcher = Dispatchers.Main
}
