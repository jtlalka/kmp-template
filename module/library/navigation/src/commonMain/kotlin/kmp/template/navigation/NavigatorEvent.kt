package kmp.template.navigation

import androidx.navigation3.runtime.NavKey
import kotlin.reflect.KClass

/**
 * Navigation events processed by the Navigator.
 */
sealed interface NavigatorEvent {

    /**
     * Navigate to a new destination.
     * @param destination The target destination which should be navigated to.
     * @param singleInstance If true, prevents duplicate instances of this destination in the backstack.
     * @param clearStackTo If provided, clears the backstack up to the specified route type.
     */
    data class NavigateTo(
        val destination: NavKey,
        val singleInstance: Boolean = true,
        val clearStackTo: KClass<*>? = null
    ) : NavigatorEvent

    /**
     * Replace the current destination with a new one.
     * @param destination The target destination which should replace the current one.
     */
    data class ReplaceTo(
        val destination: NavKey
    ) : NavigatorEvent

    /**
     * Navigate back in the backstack.
     * @param clearStackTo If provided, clears the backstack up to the specified route type.
     */
    data class NavigateUp(
        val clearStackTo: KClass<*>? = null
    ) : NavigatorEvent
}
