package kmp.template.preferences.model

import kmp.template.preferences.model.KeyType.BOOLEAN
import kmp.template.preferences.model.KeyType.BYTE
import kmp.template.preferences.model.KeyType.CHAR
import kmp.template.preferences.model.KeyType.DOUBLE
import kmp.template.preferences.model.KeyType.FLOAT
import kmp.template.preferences.model.KeyType.INT
import kmp.template.preferences.model.KeyType.LONG
import kmp.template.preferences.model.KeyType.OBJECT
import kmp.template.preferences.model.KeyType.STRING
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer

@ConsistentCopyVisibility
data class Key<T : Any> internal constructor(
    val name: String,
    val type: KeyType,
    val serializer: KSerializer<T>
) {

    override fun equals(other: Any?): Boolean =
        other is Key<*> && name == other.name && type == other.type &&
                serializer.descriptor == other.serializer.descriptor

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + type.hashCode()
        result = 31 * result + serializer.descriptor.hashCode()
        return result
    }

    @Suppress("TooManyFunctions")
    companion object {

        // primitives
        fun stringKey(name: String) = Key(name, STRING, String.serializer())
        fun intKey(name: String) = Key(name, INT, Int.serializer())
        fun longKey(name: String) = Key(name, LONG, Long.serializer())
        fun booleanKey(name: String) = Key(name, BOOLEAN, Boolean.serializer())
        fun floatKey(name: String) = Key(name, FLOAT, Float.serializer())
        fun doubleKey(name: String) = Key(name, DOUBLE, Double.serializer())
        fun charKey(name: String) = Key(name, CHAR, Char.serializer())
        fun byteKey(name: String) = Key(name, BYTE, Byte.serializer())

        // complex
        fun <T : Any> objectKey(name: String, serializer: KSerializer<T>) = Key(name, OBJECT, serializer)
    }
}
