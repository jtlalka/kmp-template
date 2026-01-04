package kmp.template.navigation

import androidx.compose.runtime.Stable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

/**
 * Cross‑platform navigation facade used by UI layers to drive screen changes.
 *
 * Implementations are responsible for maintaining a back stack of [NavKey] entries
 * and executing [NavigatorEvent] requests such as forward navigation, replacement,
 * and navigating up.
 */
@Stable
interface Navigator {

    val backStack: NavBackStack<NavKey>

    fun navigate(effect: NavigatorEvent)
}
