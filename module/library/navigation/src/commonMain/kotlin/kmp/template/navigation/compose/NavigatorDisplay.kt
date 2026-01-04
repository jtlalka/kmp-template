package kmp.template.navigation.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kmp.template.navigation.Navigator

/**
 * Renders the current navigation back stack using Navigation3's [NavDisplay].
 *
 * Apply this composable near the root of your screen hierarchy to display
 * destinations from [navigator]'s back stack with default decorators for
 * saveable state and ViewModel storage.
 */
@Composable
fun NavigatorDisplay(
    navigator: Navigator,
    entryProvider: (key: NavKey) -> NavEntry<NavKey>,
    modifier: Modifier = Modifier
) {
    NavDisplay(
        modifier = modifier,
        backStack = navigator.backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider
    )
}
