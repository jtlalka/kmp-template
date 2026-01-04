package kmp.template.environment

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class EnvironmentTypeTest {

    @Test
    fun `returns enum values in expected order when entries is called`() {
        val result = EnvironmentType.entries

        assertContentEquals(
            expected = listOf(
                EnvironmentType.ANDROID,
                EnvironmentType.IOS,
                EnvironmentType.JVM,
                EnvironmentType.WEB
            ),
            actual = result
        )
    }

    @Test
    fun `returns expected enum names when name is called`() {
        assertEquals("ANDROID", EnvironmentType.ANDROID.name)
        assertEquals("IOS", EnvironmentType.IOS.name)
        assertEquals("JVM", EnvironmentType.JVM.name)
        assertEquals("WEB", EnvironmentType.WEB.name)
    }
}
