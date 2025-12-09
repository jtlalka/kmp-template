package kmp.template.foundation.flow

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class CompletableFlow<T>(
    private val flow: Flow<T>,
    private val result: MutableList<T> = mutableListOf(),
    private var job: Job? = null
) {

    fun collect(coroutineScope: CoroutineScope) {
        job?.cancel()
        job = coroutineScope.launch {
            flow.toList(result)
        }
    }

    fun complete(): List<T> {
        job?.cancel()
        job = null
        return result.toList()
    }

    companion object {
        fun <T> Flow<T>.collect(scope: CoroutineScope): CompletableFlow<T> =
            CompletableFlow(flow = this).apply { collect(scope) }
    }
}
