package kmp.template.navigation.internal

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlin.reflect.KClass

internal class BackstackHandler {

    internal fun clearBackStack(
        backStack: NavBackStack<NavKey>,
        clearStackToInstance: KClass<*>?,
        clearStackToDestination: NavKey?,
        clearWithTarget: Boolean
    ) {
        if (clearStackToInstance != null) {
            backStack.clearStackToInstance(clearStackToInstance, clearWithTarget)
        }
        if (clearStackToDestination != null) {
            backStack.clearStackToDestination(clearStackToDestination, clearWithTarget)
        }
    }

    private fun NavBackStack<NavKey>.clearStackToInstance(
        navClass: KClass<*>,
        clearWithTarget: Boolean
    ) = clearStackToPredicate(
        clearWithTarget = clearWithTarget,
        predicate = { navClass.isInstance(it) }
    )

    private fun NavBackStack<NavKey>.clearStackToDestination(
        destination: NavKey,
        clearWithTarget: Boolean
    ) = clearStackToPredicate(
        clearWithTarget = clearWithTarget,
        predicate = { it == destination }
    )

    private fun NavBackStack<NavKey>.clearStackToPredicate(
        clearWithTarget: Boolean,
        predicate: (NavKey) -> Boolean
    ) {
        val index = indexOfLast(predicate)

        if (index >= 0) {
            val targetIndex = when (clearWithTarget) {
                true -> index
                false -> index + 1
            }
            repeat(size - targetIndex) {
                removeAt(size - 1)
            }
        }
    }
}
