package kmp.template.navigation.internal

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent

internal class NavigatorPreview(initialRoute: NavKey) : Navigator {

    override val backStack: NavBackStack<NavKey> = NavBackStack(initialRoute)

    override fun navigate(effect: NavigatorEvent) {
        // not implemented for preview
    }
}
