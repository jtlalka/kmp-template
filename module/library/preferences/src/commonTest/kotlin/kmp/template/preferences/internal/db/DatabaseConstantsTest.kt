package kmp.template.preferences.internal.db

import kotlin.test.Test
import kotlin.test.assertEquals

class DatabaseConstantsTest {

    private val tested = DatabaseConstants

    @Test
    fun `verify that DB_NAME is equal to expected value`() {
        val result = tested.DB_NAME

        assertEquals("preferences.db", result)
    }
}
