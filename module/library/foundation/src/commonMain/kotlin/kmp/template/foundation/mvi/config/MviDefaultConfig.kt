package kmp.template.foundation.mvi.config

import kmp.template.foundation.mvi.config.dispatcher.MviDispatcher
import kmp.template.foundation.mvi.config.exception.MviExceptionHandler
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler

/**
 * Default, minimalistic MVI (Redux) container configuration.
 */
data class MviDefaultConfig(
    override val exceptionHandler: CoroutineExceptionHandler = MviExceptionHandler(),
    override val launchDispatcher: CoroutineDispatcher = MviDispatcher.main
) : MviConfig
