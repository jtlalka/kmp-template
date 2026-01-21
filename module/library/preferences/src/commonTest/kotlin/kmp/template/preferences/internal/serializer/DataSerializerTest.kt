package kmp.template.preferences.internal.serializer

import kmp.template.preferences.model.Key.Companion.booleanKey
import kmp.template.preferences.model.Key.Companion.byteKey
import kmp.template.preferences.model.Key.Companion.charKey
import kmp.template.preferences.model.Key.Companion.doubleKey
import kmp.template.preferences.model.Key.Companion.floatKey
import kmp.template.preferences.model.Key.Companion.intKey
import kmp.template.preferences.model.Key.Companion.longKey
import kmp.template.preferences.model.Key.Companion.stringKey
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.serialization.SerializationException

class DataSerializerTest {

    private val tested = DataSerializer()

    @Test
    fun `verifies encode and decode for value type - string`() {
        val key = stringKey("KEY")
        val value = "hello"

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - int`() {
        val key = intKey("KEY")
        val value = 42

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - long`() {
        val key = longKey("KEY")
        val value = 123_456_789L

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - boolean`() {
        val key = booleanKey("KEY")
        val value = true

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - float`() {
        val key = floatKey("KEY")
        val value = 3.14f

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - double with digits`() {
        val key = doubleKey("KEY")
        val value = 2.718281828868698

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - large double`() {
        val key = doubleKey("KEY")
        val value = 20000000000000000000000000000000000.0

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - char`() {
        val key = charKey("KEY")
        val value = 'a'

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `verifies encode and decode for value type - byte`() {
        val key = byteKey("KEY")
        val value = 10.toByte()

        val encoded = tested.encode(key.serializer, value)
        val decoded = tested.decode(key.serializer, encoded)

        assertEquals(value, decoded)
    }

    @Test
    fun `throws exception when decode is used with incompatible type`() {
        val key = doubleKey("KEY")

        assertFailsWith<SerializationException> {
            tested.decode(key.serializer, "BUM")
        }
    }

    @Test
    fun `throws exception when decode is used with not strict boolean string value`() {
        val key = booleanKey("KEY")

        assertFailsWith<IllegalArgumentException> {
            tested.decode(key.serializer, "1")
        }
    }
}
