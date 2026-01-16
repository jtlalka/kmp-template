package kmp.template.preferences.internal.serializer.model

import kotlin.reflect.KClass

internal enum class SerializedType(
    val kClass: KClass<*>
) {
    STRING(String::class),
    INT(Int::class),
    LONG(Long::class),
    BOOLEAN(Boolean::class),
    FLOAT(Float::class),
    DOUBLE(Double::class)
}
