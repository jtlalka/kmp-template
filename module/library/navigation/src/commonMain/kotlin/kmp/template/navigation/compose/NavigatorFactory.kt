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

    val navigator: Navigator = remember {
        NavigatorController(backStack = backStack)
    }

    return navigator
}

@Composable
fun navigatorPreview(initialRoute: NavKey): Navigator = NavigatorPreview(initialRoute)
