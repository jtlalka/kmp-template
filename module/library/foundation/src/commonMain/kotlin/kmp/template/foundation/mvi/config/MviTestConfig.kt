package kmp.template.foundation.mvi.config

import kmp.template.foundation.mvi.config.exception.MviTestExceptionHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Unit test MVI (Redux) container configuration.
 */
class MviTestConfig(testDispatcher: CoroutineDispatcher) : MviConfig {
    override val exceptionHandler: CoroutineExceptionHandler = MviTestExceptionHandler()
    override val launchDispatcher: CoroutineDispatcher = testDispatcher
}
