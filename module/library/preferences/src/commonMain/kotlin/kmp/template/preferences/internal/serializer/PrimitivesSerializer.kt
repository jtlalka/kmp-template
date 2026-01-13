package kmp.template.preferences.internal.serializer

import kmp.template.preferences.exception.UnsupportedValueException
import kmp.template.preferences.internal.serializer.model.SerializedModel
import kmp.template.preferences.internal.serializer.model.SerializedType

@Suppress("UNCHECKED_CAST")
internal class PrimitivesSerializer {

    fun encode(value: Any) = SerializedModel(
        value = value.toString(),
        type = value.toSerializedType()
    )

    private fun Any.toSerializedType(): SerializedType = when (this) {
        is String -> SerializedType.STRING
        is Int -> SerializedType.INT
        is Long -> SerializedType.LONG
        is Boolean -> SerializedType.BOOLEAN
        is Float -> SerializedType.FLOAT
        is Double -> SerializedType.DOUBLE
        else -> throw UnsupportedValueException(this)
    }

    fun <T : Any> decode(
        serialized: SerializedModel
    ): T = when (serialized.type) {
        SerializedType.STRING -> serialized.value.castOrThrow()
        SerializedType.INT -> serialized.value.toInt().castOrThrow()
        SerializedType.LONG -> serialized.value.toLong().castOrThrow()
        SerializedType.BOOLEAN -> serialized.value.toBooleanStrict().castOrThrow()
        SerializedType.FLOAT -> serialized.value.toFloat().castOrThrow()
        SerializedType.DOUBLE -> serialized.value.toDouble().castOrThrow()
    }

    private fun <T : Any> Any.castOrThrow(): T =
        this as? T ?: throw UnsupportedValueException(this)
}
