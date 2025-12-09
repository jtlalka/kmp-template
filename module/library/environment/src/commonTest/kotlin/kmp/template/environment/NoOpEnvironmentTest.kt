package kmp.template.environment

import kotlin.test.Test
import kotlin.test.assertEquals

class NoOpEnvironmentTest {

    @Test
    fun `returns defaults values when not overridden`() {
        val given = NoOpEnvironment(type = EnvironmentType.JVM)

        assertEquals("application", given.applicationId)
        assertEquals("1.0.0", given.versionName)
        assertEquals("1", given.versionCode)
        assertEquals(false, given.debug)
    }

    @Test
    fun `returns custom values when values are provided`() {
        val given = NoOpEnvironment(
            type = EnvironmentType.WEB,
            applicationId = "web.app",
            versionName = "2.3.4",
            versionCode = "42",
            debug = true
        )

        assertEquals(EnvironmentType.WEB, given.type)
        assertEquals("web.app", given.applicationId)
        assertEquals("2.3.4", given.versionName)
        assertEquals("42", given.versionCode)
        assertEquals(true, given.debug)
    }
}
