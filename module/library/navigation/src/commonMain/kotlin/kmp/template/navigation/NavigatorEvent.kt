package kmp.template.navigation

import androidx.navigation3.runtime.NavKey
import kotlin.reflect.KClass

sealed interface NavigatorEvent {

    data class NavigateTo(
        val destination: NavKey,
        val singleInstance: Boolean = true,
        val clearStackTo: KClass<*>? = null
    ) : NavigatorEvent

    data class ReplaceTo(
        val destination: NavKey
    ) : NavigatorEvent

    data class NavigateUp(
        val clearStackTo: KClass<*>? = null
    ) : NavigatorEvent
}
