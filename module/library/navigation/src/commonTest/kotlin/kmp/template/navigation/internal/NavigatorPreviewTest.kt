package kmp.template.navigation.internal

import androidx.navigation3.runtime.NavKey
import kotlin.test.Test
import kotlin.test.assertEquals

class NavigatorPreviewTest {

    @Test
    fun `creates navigator preview which starts with initial route`() {
        val preview = NavigatorPreview(initialRoute = Start)

        assertEquals(listOf(Start), preview.backStack.toList())
    }

    companion object {
        private object Start : NavKey
    }
}
