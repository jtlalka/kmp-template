package kmp.template.preferences

import kotlin.reflect.KClass

interface Preferences {

    suspend fun <T : Any> get(key: String, kClass: KClass<T>): T?

    suspend fun <T : Any> set(key: String, value: T)

    suspend fun hasKey(key: String): Boolean

    suspend fun remove(key: String)

    suspend fun clear()
}
