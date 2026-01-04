package kmp.template.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import kmp.template.navigation.Navigator
import kmp.template.navigation.internal.NavigatorController
import kmp.template.navigation.internal.NavigatorPreview
import kotlinx.serialization.modules.SerializersModule

/**
 * Creates and remembers a [Navigator] tied to the current Compose composition.
 *
 * The navigator is backed by a [NavBackStack] created with the provided
 * [SerializersModule] to support save/restore of destinations.
 */
@Composable
fun rememberNavigator(
    initialRoute: NavKey,
    serializers: SerializersModule
): Navigator {

    val backStack: NavBackStack<NavKey> = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = serializers
        },
        initialRoute
    )

    val navigator: Navigator = remember(backStack) {
        NavigatorController(backStack = backStack)
    }

    return navigator
}

/**
 * Lightweight preview helper that returns a simple [Navigator] backed by an in‑memory
 * back stack, useful for previews and tests where full state saving is not required.
 */
@Composable
fun navigatorPreview(initialRoute: NavKey): Navigator = NavigatorPreview(initialRoute)
