package kmp.template.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent.NavigateTo
import kotlin.reflect.KClass

/**
 * Navigator tab controller for menu UI components.
 * @param navigator The navigator class implementation (See [rememberNavigator] compose).
 * @param tabDestinations List of tab destinations.
 * @param startDestination The start destination for the tab bar.
 */
@Composable
fun NavigatorTabController(
    navigator: Navigator,
    tabDestinations: List<Any>,
    startDestination: Any,
    content: @Composable NavigatorTabControllerScope.(selectedId: Any) -> Unit
) {
    val scope = remember(navigator, startDestination) {
        NavigatorTabControllerScope(navigator, startDestination::class)
    }

    val selectedId = remember(navigator.backStack.lastOrNull(), tabDestinations) {
        navigator.backStack.lastOrNull {
            tabDestinations.any { route -> route == it }
        }
    }

    scope.content(selectedId ?: startDestination)
}

/**
 * Scope for [NavigatorTabController] composable.
 */
@Stable
data class NavigatorTabControllerScope(
    private val navigator: Navigator,
    private val startDestination: KClass<*>
) {

    /**
     * Default single-backstack logic for tab bar navigation.
     */
    @Stable
    fun navigateToNavigationBar(id: Any) {
        require(id is NavKey) {
            "Unsupported type: $id - in Navigation3 destination should inherit from NavKey interface."
        }
        navigator.navigate(
            NavigateTo(
                destination = id,
                singleInstance = true,
                clearStackTo = startDestination
            )
        )
    }
}
