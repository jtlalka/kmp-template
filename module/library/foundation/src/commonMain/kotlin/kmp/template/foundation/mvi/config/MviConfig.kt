package kmp.template.foundation.mvi.config

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers

/**
 * Configuration interface for MVI (Redux) architecture.
 *
 * Implementation of this interface can be added to the DI graph.
 */
interface MviConfig {

    /**
     * Managing exceptions not handled directly in MVI Intents
     */
    val exceptionHandler: CoroutineExceptionHandler

    /**
     * MVI Intents based coroutine dispatcher, recommended [Dispatchers.Main]
     */
    val launchDispatcher: CoroutineDispatcher
}
