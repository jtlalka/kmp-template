package kmp.template.feature.sample.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import kmp.template.feature.sample.navigation.SampleRoute.SampleDesignSystem
import kmp.template.feature.sample.navigation.SampleRoute.SampleLaunch
import kmp.template.feature.sample.presentation.design.SampleDesignScreen
import kmp.template.feature.sample.presentation.environment.SampleEnvironmentScreen
import kmp.template.feature.sample.presentation.launch.SampleLaunchScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack: NavBackStack<NavKey> = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(subclass = SampleLaunch::class, serializer = SampleLaunch.serializer())
                    subclass(subclass = SampleDesignSystem::class, serializer = SampleDesignSystem.serializer())
                }
            }
        },
        SampleLaunch
    )

    NavDisplay(
        modifier = modifier,
        backStack = backStack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = { key ->
            when (key) {
                is SampleLaunch -> {
                    NavEntry(key) {
                        SampleLaunchScreen()
                    }
                }
                is SampleDesignSystem -> {
                    NavEntry(key) {
                        SampleDesignScreen()
                    }
                }
                is SampleRoute.SampleEnvironment -> {
                    NavEntry(key) {
                        SampleEnvironmentScreen()
                    }
                }
                else -> error("Unknown NavKey: $key")
            }
        }
    )
}
