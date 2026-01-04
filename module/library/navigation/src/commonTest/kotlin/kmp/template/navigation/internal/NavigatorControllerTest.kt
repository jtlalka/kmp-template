package kmp.template.navigation.internal

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kmp.template.navigation.NavigatorEvent.NavigateTo
import kmp.template.navigation.NavigatorEvent.NavigateUp
import kmp.template.navigation.NavigatorEvent.ReplaceTo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class NavigatorControllerTest {

    private val tested = NavigatorController(
        backStack = NavBackStack(AKey)
    )

    @Test
    fun `navigates to new destination when navigate is called with new destination`() {
        tested.navigate(NavigateTo(destination = BKey))

        assertEquals(listOf(AKey, BKey), tested.backStack.toList())
    }

    @Test
    fun `navigates single time to destination when navigate is called several times and singleInstance is true`() {
        tested.navigate(NavigateTo(destination = BKey, singleInstance = true))
        tested.navigate(NavigateTo(destination = BKey, singleInstance = true))
        tested.navigate(NavigateTo(destination = BKey, singleInstance = true))

        assertEquals(listOf(AKey, BKey), tested.backStack.toList())
    }

    @Test
    fun `navigates several times to destination when navigate is called several times and singleInstance is false`() {
        tested.navigate(NavigateTo(destination = BKey, singleInstance = false))
        tested.navigate(NavigateTo(destination = BKey, singleInstance = false))
        tested.navigate(NavigateTo(destination = BKey, singleInstance = false))

        assertEquals(listOf(AKey, BKey, BKey, BKey), tested.backStack.toList())
    }

    @Test
    fun `replaces destination on back stack when navigate is called with ReplaceTo event`() {
        tested.navigate(ReplaceTo(destination = BKey))

        assertEquals(listOf(BKey), tested.backStack.toList())
    }

    @Test
    fun `throws exception when navigate is called with ReplaceTo event on empty backstack`() {
        tested.backStack.clear()

        assertFailsWith<NoSuchElementException> {
            tested.navigate(ReplaceTo(destination = BKey))
        }
    }

    @Test
    fun `pops destination from back stack when navigate is called with NavigateUp event`() {
        tested.navigate(NavigateTo(destination = BKey))

        tested.navigate(NavigateUp())

        assertEquals(listOf(AKey), tested.backStack.toList())
    }

    @Test
    fun `keeps back stack with single destination intact when navigate is called with NavigateUp event`() {
        tested.navigate(NavigateUp())

        assertEquals(listOf(AKey), tested.backStack.toList())
    }

    @Test
    fun `keeps back stack with single destination intact when navigate is called with NavigateUp parameter`() {
        tested.navigate(NavigateUp(clearStackTo = AKey::class))

        assertEquals(listOf(AKey), tested.backStack.toList())
    }

    @Test
    fun `keeps back stack intact when navigate is called with NavigateUp with invalid parameter`() {
        tested.navigate(NavigateUp(clearStackTo = CKey::class))

        assertEquals(listOf(AKey), tested.backStack.toList())
    }

    companion object {
        private object AKey : NavKey
        private object BKey : NavKey
        private object CKey : NavKey
    }
}
