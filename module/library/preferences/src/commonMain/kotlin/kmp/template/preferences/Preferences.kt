package kmp.template.preferences

interface Preferences {

    suspend fun <T : Any> getOrThrow(key: String): T

    suspend fun <T : Any> getOrDefault(key: String, default: T): T

    suspend fun <T : Any> set(key: String, value: T)

    suspend fun hasKey(key: String): Boolean

    suspend fun remove(key: String)

    suspend fun clear()
}
