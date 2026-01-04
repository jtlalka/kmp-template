package kmp.template.navigation.internal

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlin.test.Test
import kotlin.test.assertEquals

class BackstackHandlerTest {

    private val tested = BackstackHandler()

    @Test
    fun `clears back stack when clear is called on existing instance without target`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = B::class,
            clearStackToDestination = null,
            clearWithTarget = false
        )

        assertEquals(listOf(A(1), B), stack.toList())
    }

    @Test
    fun `clears back stack when clear is called on existing instance with target`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = B::class,
            clearStackToDestination = null,
            clearWithTarget = true
        )

        assertEquals(listOf(A(1)), stack.toList())
    }

    @Test
    fun `does nothing when clear back stack is called on the last instance without target`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = A::class,
            clearStackToDestination = null,
            clearWithTarget = false
        )

        assertEquals(listOf(A(1), B, A(2), A(3)), stack.toList())
    }

    @Test
    fun `clears back stack when clear is called on existing destination without target`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = null,
            clearStackToDestination = A(2),
            clearWithTarget = false
        )

        assertEquals(listOf(A(1), B, A(2)), stack.toList())
    }

    @Test
    fun `clears back stack when clear is called on existing destination with target`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = null,
            clearStackToDestination = A(2),
            clearWithTarget = true
        )

        assertEquals(listOf(A(1), B), stack.toList())
    }

    @Test
    fun `does nothing when clear back stack is called on the last destination without target`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = null,
            clearStackToDestination = A(3),
            clearWithTarget = false
        )

        assertEquals(listOf(A(1), B, A(2), A(3)), stack.toList())
    }

    @Test
    fun `clears whole back stack when clear is called on first destination with target`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = null,
            clearStackToDestination = A(1),
            clearWithTarget = true
        )

        assertEquals(emptyList(), stack.toList())
    }

    @Test
    fun `does nothing when clear back stack is called on empty back stack`() {
        val stack = NavBackStack<NavKey>()

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = A::class,
            clearStackToDestination = null,
            clearWithTarget = true
        )

        assertEquals(emptyList(), stack.toList())
    }

    @Test
    fun `does nothing when clear back stack is called on not existing instances`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = C::class,
            clearStackToDestination = A(100),
            clearWithTarget = true
        )

        assertEquals(listOf(A(1), B, A(2), A(3)), stack.toList())
    }

    @Test
    fun `does nothing when clear back stack is called on not existing destination`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = null,
            clearStackToDestination = A(100),
            clearWithTarget = true
        )

        assertEquals(listOf(A(1), B, A(2), A(3)), stack.toList())
    }

    @Test
    fun `clear back stack when clear is called with destination and instances`() {
        val stack = NavBackStack(A(1), B, A(2), C, B)

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = A::class,
            clearStackToDestination = B,
            clearWithTarget = true
        )

        assertEquals(listOf(A(1)), stack.toList())
    }

    @Test
    fun `does nothing when clear back stack is called without destination and instances`() {
        val stack = NavBackStack(A(1), B, A(2), A(3))

        tested.clearBackStack(
            backStack = stack,
            clearStackToInstance = null,
            clearStackToDestination = null,
            clearWithTarget = true
        )

        assertEquals(listOf(A(1), B, A(2), A(3)), stack.toList())
    }

    companion object {
        private data class A(val id: Int) : NavKey
        private data object B : NavKey
        private data object C : NavKey
    }
}
