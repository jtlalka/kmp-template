package kmp.template.preferences

import kmp.template.preferences.exception.NoPreferencesKeyException

suspend inline fun <reified T : Any> Preferences.edit(key: String, default: T, block: (T) -> T) =
    set(key, block(getOrDefault(key, default)))

suspend inline fun <reified T : Any> Preferences.getOrDefault(key: String, default: T): T =
    get(key, T::class) ?: default

suspend inline fun <reified T : Any> Preferences.getOrThrow(key: String): T =
    get(key, T::class) ?: throw NoPreferencesKeyException(key)
