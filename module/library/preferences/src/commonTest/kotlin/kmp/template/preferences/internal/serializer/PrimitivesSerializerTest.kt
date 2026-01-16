package kmp.template.preferences.internal.serializer

import kmp.template.preferences.exception.UnsupportedValueException
import kmp.template.preferences.internal.serializer.model.SerializedModel
import kmp.template.preferences.internal.serializer.model.SerializedType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class PrimitivesSerializerTest {

    private val tested = PrimitivesSerializer()

    @Test
    fun `verifies encode and decode for value type - string`() {
        val value = "hello"

        val encoded = tested.encode(value)
        val decoded = tested.decode<String>(encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - int`() {
        val value = 42

        val encoded = tested.encode(value)
        val decoded = tested.decode<Int>(encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - long`() {
        val value = 123_456_789L

        val encoded = tested.encode(value)
        val decoded = tested.decode<Long>(encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - boolean`() {
        val value = true

        val encoded = tested.encode(value)
        val decoded = tested.decode<Boolean>(encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - float`() {
        val value = 3.14f

        val encoded = tested.encode(value)
        val decoded = tested.decode<Float>(encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - double with digits`() {
        val value = 2.718281828868698

        val encoded = tested.encode(value)
        val decoded = tested.decode<Double>(encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - large double`() {
        val value = 20000000000000000000000000000000000.0

        val encoded = tested.encode(value)
        val decoded = tested.decode<Double>(encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `throws exception when encode is used with unsupported value type`() {
        assertFailsWith<UnsupportedValueException> {
            tested.encode(object {})
        }
    }

    @Test
    fun `throws exception when decode is used with incompatible type`() {
        assertFailsWith<NumberFormatException> {
            tested.decode<Boolean>(SerializedModel("bum", SerializedType.INT))
        }
    }

    @Test
    fun `throws exception when decode is used with not strict boolean string value`() {
        assertFailsWith<IllegalArgumentException> {
            tested.decode<Boolean>(SerializedModel("1", SerializedType.BOOLEAN))
        }
    }
}
