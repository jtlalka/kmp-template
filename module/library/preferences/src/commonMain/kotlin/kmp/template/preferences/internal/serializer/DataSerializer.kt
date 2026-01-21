package kmp.template.preferences.internal.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json

internal class DataSerializer {

    fun <T : Any> encode(
        serializer: KSerializer<T>,
        value: T
    ): String = json.encodeToString(
        serializer = serializer,
        value = value
    )

    fun <T : Any> decode(
        serializer: KSerializer<T>,
        value: String
    ): T = json.decodeFromString(
        deserializer = serializer,
        string = value
    )

    companion object {
        private val json: Json = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }
}
