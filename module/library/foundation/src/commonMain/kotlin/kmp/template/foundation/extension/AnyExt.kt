package kmp.template.foundation.extension

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

suspend fun <T> runWithMinDuration(
    minDuration: Duration = 300.milliseconds,
    block: suspend () -> T
): T = coroutineScope {
    val delayJob = launch { delay(minDuration) }
    val blockJob = async { block() }

    blockJob.await().also {
        delayJob.join()
    }
}
