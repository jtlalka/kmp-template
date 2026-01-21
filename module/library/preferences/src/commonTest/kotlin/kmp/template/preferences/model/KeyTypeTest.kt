package kmp.template.preferences.model

import kotlin.test.Test
import kotlin.test.assertEquals

class KeyTypeTest {

    private val tested = KeyType.entries

    @Test
    fun `verifies enum entries names and expected order`() {
        assertEquals(
            expected = listOf("STRING", "INT", "LONG", "BOOLEAN", "FLOAT", "DOUBLE", "CHAR", "BYTE", "OBJECT"),
            actual = tested.map { it.name }
        )
    }
}
