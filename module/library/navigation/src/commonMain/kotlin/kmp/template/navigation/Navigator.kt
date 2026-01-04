package kmp.template.navigation

import androidx.compose.runtime.Immutable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

@Immutable
interface Navigator {

    val backStack: NavBackStack<NavKey>

    fun navigate(effect: NavigatorEvent)
}
