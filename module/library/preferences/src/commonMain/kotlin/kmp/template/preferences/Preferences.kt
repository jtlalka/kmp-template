package kmp.template.preferences

import kmp.template.preferences.exception.NoPreferencesKeyException
import kmp.template.preferences.model.Key
import kotlinx.coroutines.flow.Flow

interface Preferences {

    fun <T : Any> observe(key: Key<T>): Flow<T?>

    suspend fun <T : Any> get(key: Key<T>): T?

    suspend fun <T : Any> set(key: Key<T>, value: T)

    suspend fun <T : Any> edit(key: Key<T>, block: (T?) -> T)

    suspend fun <T : Any> hasKey(key: Key<T>): Boolean

    suspend fun remove(key: Key<*>)

    suspend fun clear()
}

suspend fun <T : Any> Preferences.getOrThrow(key: Key<T>): T =
    get(key) ?: throw NoPreferencesKeyException(key)

suspend fun <T : Any> Preferences.getOrDefault(key: Key<T>, default: T): T =
    get(key) ?: default
