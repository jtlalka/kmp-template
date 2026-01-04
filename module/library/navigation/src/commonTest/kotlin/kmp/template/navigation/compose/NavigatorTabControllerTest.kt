package kmp.template.navigation.compose

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.mokkery.answering.calls
import dev.mokkery.answering.returns
import dev.mokkery.every
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent.NavigateTo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NavigatorTabControllerTest {

    private object TabA : NavKey
    private object TabB : NavKey

    private val navigator = mock<Navigator> {
        every { backStack } calls { NavBackStack(TabA) }
        every { navigate(any()) } returns Unit
    }

    private val tested = NavigatorTabControllerScope(
        navigator = navigator,
        startDestination = TabA::class
    )

    @Test
    fun `navigates to TabB and clears back stack to start destination when TabB is selected`() {
        tested.navigateToNavigationBar(id = TabB)

        verify {
            navigator.navigate(
                effect = NavigateTo(
                    destination = TabB,
                    singleInstance = true,
                    clearStackTo = TabA::class
                )
            )
        }
    }

    @Test
    fun `throws exception when destination id does not inherit from NavKey`() {
        val invalidKey = "invalidKey"

        val error = assertFailsWith<IllegalArgumentException> {
            tested.navigateToNavigationBar(id = invalidKey)
        }

        assertEquals(
            expected = "Unsupported type: $invalidKey - in Navigation3 destination should inherit from NavKey interface.",
            actual = error.message
        )
    }
}
