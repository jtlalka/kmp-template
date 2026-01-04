package kmp.template.navigation.internal

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent
import kmp.template.navigation.NavigatorEvent.NavigateTo
import kmp.template.navigation.NavigatorEvent.NavigateUp
import kmp.template.navigation.NavigatorEvent.ReplaceTo
import kotlin.reflect.KClass

internal class NavigatorController(
    override val backStack: NavBackStack<NavKey>,
    internal val backstackHandler: BackstackHandler = BackstackHandler()
) : Navigator {

    override fun navigate(effect: NavigatorEvent) {
        when (effect) {
            is NavigateTo -> onNavigate(
                destination = effect.destination,
                singleInstance = effect.singleInstance,
                clearStackTo = effect.clearStackTo,
                clearWithTarget = false
            )
            is ReplaceTo -> onNavigate(
                destination = effect.destination,
                singleInstance = false,
                clearStackTo = backStack.last()::class,
                clearWithTarget = true
            )
            is NavigateUp -> onNavigateBack(
                clearStackTo = effect.clearStackTo
            )
        }
    }

    private fun onNavigate(
        destination: NavKey,
        singleInstance: Boolean,
        clearStackTo: KClass<*>?,
        clearWithTarget: Boolean
    ) {
        backstackHandler.clearBackStack(
            backStack = backStack,
            clearStackToInstance = clearStackTo,
            clearStackToDestination = destination.takeIf { singleInstance },
            clearWithTarget = clearWithTarget
        )

        if (backStack.lastOrNull() != destination || !singleInstance) {
            backStack.add(destination)
        }
    }

    private fun onNavigateBack(
        clearStackTo: KClass<*>?
    ) {
        if (backStack.size > 1) {
            backstackHandler.clearBackStack(
                backStack = backStack,
                clearStackToInstance = clearStackTo,
                clearStackToDestination = backStack.last().takeIf { clearStackTo == null },
                clearWithTarget = clearStackTo == null
            )
        }
    }
}
