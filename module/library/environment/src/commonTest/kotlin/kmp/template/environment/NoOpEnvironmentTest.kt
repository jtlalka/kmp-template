package kmp.template.environment

import kotlin.test.Test
import kotlin.test.assertEquals

class NoOpEnvironmentTest {

    @Test
    fun `returns default values when not overwritten`() {
        val result = NoOpEnvironment(type = EnvironmentType.JVM)

        assertEquals(EnvironmentType.JVM, result.type)
        assertEquals("application", result.applicationId)
        assertEquals("1.0.0", result.versionName)
        assertEquals("1", result.versionCode)
        assertEquals(false, result.debug)
    }

    @Test
    fun `returns custom values when values are provided`() {
        val result = NoOpEnvironment(
            type = EnvironmentType.WEB,
            applicationId = "web.app",
            versionName = "2.3.4",
            versionCode = "42",
            debug = true
        )

        assertEquals(EnvironmentType.WEB, result.type)
        assertEquals("web.app", result.applicationId)
        assertEquals("2.3.4", result.versionName)
        assertEquals("42", result.versionCode)
        assertEquals(true, result.debug)
    }
}
