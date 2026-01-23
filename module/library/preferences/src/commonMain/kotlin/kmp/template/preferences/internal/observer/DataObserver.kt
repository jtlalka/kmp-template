package kmp.template.preferences.internal.observer

import kmp.template.preferences.model.Key
import kotlinx.coroutines.flow.Flow

internal interface DataObserver {

    fun <T : Any> observeKey(key: Key<T>): Flow<T?>

    fun <T : Any> updateKey(key: Key<T>, value: T?)

    fun clearValues()
}
