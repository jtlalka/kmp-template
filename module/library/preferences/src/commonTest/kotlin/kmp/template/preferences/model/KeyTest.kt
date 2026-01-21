package kmp.template.preferences.model

import kmp.template.preferences.model.Key.Companion.booleanKey
import kmp.template.preferences.model.Key.Companion.byteKey
import kmp.template.preferences.model.Key.Companion.charKey
import kmp.template.preferences.model.Key.Companion.doubleKey
import kmp.template.preferences.model.Key.Companion.floatKey
import kmp.template.preferences.model.Key.Companion.intKey
import kmp.template.preferences.model.Key.Companion.longKey
import kmp.template.preferences.model.Key.Companion.objectKey
import kmp.template.preferences.model.Key.Companion.stringKey
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals
import kotlin.test.assertTrue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer

class KeyTest {

    @Test
    fun `stringKey builds STRING key with proper name and serializer`() {
        val key = stringKey("A")

        assertEquals(
            expected = Key(
                name = "A",
                type = KeyType.STRING,
                serializer = String.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `intKey builds INT key with proper name and serializer`() {
        val key = intKey("I")

        assertEquals(
            expected = Key(
                name = "I",
                type = KeyType.INT,
                serializer = Int.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `longKey builds LONG key with proper name and serializer`() {
        val key = longKey("L")

        assertEquals(
            expected = Key(
                name = "L",
                type = KeyType.LONG,
                serializer = Long.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `booleanKey builds BOOLEAN key with proper name and serializer`() {
        val key = booleanKey("B")

        assertEquals(
            expected = Key(
                name = "B",
                type = KeyType.BOOLEAN,
                serializer = Boolean.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `floatKey builds FLOAT key with proper name and serializer`() {
        val key = floatKey("F")

        assertEquals(
            expected = Key(
                name = "F",
                type = KeyType.FLOAT,
                serializer = Float.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `doubleKey builds DOUBLE key with proper name and serializer`() {
        val key = doubleKey("D")

        assertEquals(
            expected = Key(
                name = "D",
                type = KeyType.DOUBLE,
                serializer = Double.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `charKey builds CHAR key with proper name and serializer`() {
        val key = charKey("C")

        assertEquals(
            expected = Key(
                name = "C",
                type = KeyType.CHAR,
                serializer = Char.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `byteKey builds BYTE key with proper name and serializer`() {
        val key = byteKey("BY")

        assertEquals(
            expected = Key(
                name = "BY",
                type = KeyType.BYTE,
                serializer = Byte.serializer()
            ),
            actual = key
        )
    }

    @Test
    fun `objectKey builds OBJECT key with proper name and provided serializer`() {
        val listSerializer = ListSerializer(String.serializer())

        val key = objectKey("LIST", listSerializer)

        assertEquals(
            expected = Key(
                name = "LIST",
                type = KeyType.OBJECT,
                serializer = listSerializer
            ),
            actual = key
        )
    }

    @Test
    fun `keys equality respects name type and serializer for primitive keys`() {
        val key1 = stringKey("SAME")
        val key2 = stringKey("SAME")

        assertEquals(key1, key2)
    }

    @Test
    fun `keys equality respects name type and serializer for object keys`() {
        val serializer = ListSerializer(String.serializer())
        val keyName = "LIST"

        val key1 = objectKey(keyName, serializer)
        val key2 = objectKey(keyName, serializer)

        assertEquals(key1, key2)
    }

    @Test
    fun `object keys with same name and serializer but different type are not equal`() {
        val keyName = "name"

        val key1 = stringKey(keyName)
        val key2 = objectKey(keyName, String.serializer())

        assertNotEquals(key1, key2)
    }

    @Test
    fun `object keys with same name and type but different serializer are not equal`() {
        val serializer1: KSerializer<List<String>> = ListSerializer(String.serializer())
        val serializer2: KSerializer<List<Int>> = ListSerializer(Int.serializer())
        val keyName = "LIST"

        val key1 = objectKey(keyName, serializer1)
        val key2 = objectKey(keyName, serializer2)

        assertTrue(key1 != key2)
    }

    @Test
    fun `object keys with same name and type but different serializer INSTANCE are equal`() {
        val serializerA: KSerializer<List<String>> = ListSerializer(String.serializer())
        val serializerB: KSerializer<List<String>> = ListSerializer(String.serializer())
        val keyName = "LIST"

        val key1 = objectKey(keyName, serializerA)
        val key2 = objectKey(keyName, serializerB)

        assertEquals(key1, key2)
    }

    @Test
    fun `object key with same name type and primitive serializer is not equal to primitive key`() {
        val keyName = "LIST"

        val key1 = objectKey(keyName, String.serializer())
        val key2 = stringKey(keyName)

        assertNotEquals(key1, key2)
    }
}
