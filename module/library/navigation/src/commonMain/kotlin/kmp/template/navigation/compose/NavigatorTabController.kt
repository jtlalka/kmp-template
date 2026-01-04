package kmp.template.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavKey
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent.NavigateTo
import kotlin.reflect.KClass

/**
 * Navigator tab controller for menu UI components.
 */
@Composable
fun NavigatorTabController(
    navigator: Navigator,
    tabRoutes: List<Any>,
    startDestination: Any,
    fullScreenDestinations: List<KClass<*>> = emptyList(),
    content: @Composable NavigatorTabControllerScope.(selectedId: Any) -> Unit
) {
    val scope = remember(navigator, startDestination) {
        NavigatorTabControllerScope(navigator, startDestination::class)
    }

    val showContent = remember(navigator.backStack.lastOrNull()) {
        navigator.backStack.lastOrNull()?.let {
            fullScreenDestinations.none { route -> route == it::class }
        } ?: false
    }

    val selectedId = remember(navigator.backStack.lastOrNull()) {
        navigator.backStack.lastOrNull {
            tabRoutes.any { route -> route == it }
        }
    }

    if (showContent && selectedId != null) {
        scope.content(selectedId)
    }
}

@Immutable
data class NavigatorTabControllerScope(
    private val navigator: Navigator,
    private val startDestination: KClass<*>
) {

    /**
     * Default single-backstack logic for tab bar navigation.
     */
    @Stable
    fun navigateToNavigationBar(id: Any) {
        if (id is NavKey) {
            navigator.navigate(
                NavigateTo(
                    destination = id,
                    singleInstance = true,
                    clearStackTo = startDestination
                )
            )
        }
    }
}
