package kmp.template.preferences.internal.observer

import kmp.template.preferences.model.Key
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

internal class DataObserver {

    private val flows = mutableMapOf<Key<*>, MutableSharedFlow<Any?>>()
    private val lock = SynchronizedObject()

    @Suppress("UNCHECKED_CAST")
    fun <T : Any> observeKey(key: Key<T>): Flow<T?> =
        synchronized(lock) { flows.getOrPut(key) { getNewSharedFlow() } } as Flow<T?>

    private fun getNewSharedFlow() = MutableSharedFlow<Any?>(
        replay = 0,
        extraBufferCapacity = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )

    fun <T : Any> updateKey(key: Key<T>, value: T?) {
        synchronized(lock) { flows[key] }?.tryEmit(value)
    }

    fun clearValues() {
        synchronized(lock) { flows.values.toList() }
            .forEach { it.tryEmit(null) }
    }
}
