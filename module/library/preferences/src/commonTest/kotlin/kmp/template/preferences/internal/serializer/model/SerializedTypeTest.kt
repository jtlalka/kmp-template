package kmp.template.preferences.internal.serializer.model

import kotlin.test.Test
import kotlin.test.assertEquals

class SerializedTypeTest {

    @Test
    fun `verify enum name and kClass for STRING value`() {
        val tested = SerializedType.STRING

        assertEquals(
            expected = Pair("STRING", String::class),
            actual = tested.name to tested.kClass
        )
    }

    @Test
    fun `verify enum name and kClass for INT value`() {
        val tested = SerializedType.INT

        assertEquals(
            expected = Pair("INT", Int::class),
            actual = tested.name to tested.kClass
        )
    }

    @Test
    fun `verify enum name and kClass for LONG value`() {
        val tested = SerializedType.LONG

        assertEquals(
            expected = Pair("LONG", Long::class),
            actual = tested.name to tested.kClass
        )
    }

    @Test
    fun `verify enum name and kClass for BOOLEAN value`() {
        val tested = SerializedType.BOOLEAN

        assertEquals(
            expected = Pair("BOOLEAN", Boolean::class),
            actual = tested.name to tested.kClass
        )
    }

    @Test
    fun `verify enum name and kClass for FLOAT value`() {
        val tested = SerializedType.FLOAT

        assertEquals(
            expected = Pair("FLOAT", Float::class),
            actual = tested.name to tested.kClass
        )
    }

    @Test
    fun `verify enum name and kClass for DOUBLE value`() {
        val tested = SerializedType.DOUBLE

        assertEquals(
            expected = Pair("DOUBLE", Double::class),
            actual = tested.name to tested.kClass
        )
    }
}
