package kmp.template.preferences.internal.observer

import kmp.template.preferences.model.Key
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class DataFlowObserver : DataObserver {

    private val flows = mutableMapOf<Key<*>, MutableSharedFlow<Any?>>()
    private val lock = SynchronizedObject()

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> observeKey(key: Key<T>): Flow<T?> =
        synchronized(lock) { flows.getOrPut(key) { getNewFlow() } } as Flow<T?>

    override fun <T : Any> updateKey(key: Key<T>, value: T?) {
        synchronized(lock) { flows[key] }?.tryEmit(value)
    }

    override fun clearValues() {
        synchronized(lock) { flows.values.toList() }
            .forEach { it.tryEmit(null) }
    }

    companion object {

        private fun getNewFlow() = MutableSharedFlow<Any?>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }
}
