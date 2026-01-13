package kmp.template.feature.sample.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.component.navigation.AppNavigationBar
import kmp.template.design.component.navigation.AppNavigationUiModel
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.navigation.SampleRoute.AboutDestination
import kmp.template.feature.sample.navigation.SampleRoute.HomeDestination
import kmp.template.feature.sample.navigation.SampleRoute.SampleDesignDestination
import kmp.template.feature.sample.navigation.SampleRoute.SampleEnvironmentDestination
import kmp.template.feature.sample.navigation.SampleRoute.StorageDemoDestination
import kmp.template.feature.sample.presentation.about.SampleAboutScreen
import kmp.template.feature.sample.presentation.design.SampleDesignScreen
import kmp.template.feature.sample.presentation.environment.SampleEnvironmentScreen
import kmp.template.feature.sample.presentation.home.SampleHomeScreen
import kmp.template.feature.sample.presentation.storage.StorageDemoScreen
import kmp.template.navigation.Navigator
import kmp.template.navigation.compose.NavigatorDisplay
import kmp.template.navigation.compose.NavigatorTabController
import kmp.template.navigation.compose.navigatorPreview
import kmp.template.navigation.compose.rememberNavigator
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.sample_navigation_bar_about
import kmp_template.module.feature.sample.generated.resources.sample_navigation_bar_home
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Suppress("LongMethod")
@Composable
fun SampleScreen() {

    val viewModel: SampleViewModel = koinViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    val navigator: Navigator = rememberNavigator(
        initialRoute = HomeDestination,
        serializers = SerializersModule {
            polymorphic(NavKey::class) {
                subclass(subclass = SampleDesignDestination::class, serializer = SampleDesignDestination.serializer())
                subclass(subclass = SampleEnvironmentDestination::class, serializer = SampleEnvironmentDestination.serializer())
                subclass(subclass = HomeDestination::class, serializer = HomeDestination.serializer())
                subclass(subclass = AboutDestination::class, serializer = AboutDestination.serializer())
                subclass(subclass = StorageDemoDestination::class, serializer = StorageDemoDestination.serializer())
            }
        }
    )

    val navEntryProvider: (key: NavKey) -> NavEntry<NavKey> = remember(navigator) {
        entryProvider {
            entry<HomeDestination> {
                SampleHomeScreen(
                    viewModel = koinViewModel(),
                    navigator = navigator
                )
            }
            entry<SampleDesignDestination> {
                SampleDesignScreen(
                    viewModel = koinViewModel(),
                    navigator = navigator
                )
            }
            entry<SampleEnvironmentDestination> {
                SampleEnvironmentScreen(
                    viewModel = koinViewModel(),
                    navigator = navigator
                )
            }
            entry<StorageDemoDestination> {
                StorageDemoScreen(
                    viewModel = koinViewModel(),
                    navigator = navigator
                )
            }
            entry<AboutDestination> {
                SampleAboutScreen(
                    viewModel = koinViewModel(),
                    navigator = navigator
                )
            }
        }
    }

    SampleScreen(
        viewState = viewState,
        navigator = navigator,
        content = {
            NavigatorDisplay(
                navigator = navigator,
                entryProvider = navEntryProvider,
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}

@Composable
private fun SampleScreen(
    viewState: SampleViewState,
    navigator: Navigator,
    content: @Composable () -> Unit
) = Column {
    AnimatedVisibility(
        visible = viewState.screenReady,
        modifier = Modifier.weight(1f)
    ) {
        content()
    }
    SampleNavigationBar(
        navigator = navigator
    )
}

@Composable
private fun SampleNavigationBar(
    navigator: Navigator
) {
    val navigationTabs = listOf(
        AppNavigationUiModel(
            id = HomeDestination,
            label = stringResource(Res.string.sample_navigation_bar_home),
            icon = AppTheme.icons.home
        ),
        AppNavigationUiModel(
            id = AboutDestination,
            label = stringResource(Res.string.sample_navigation_bar_about),
            icon = AppTheme.icons.infoCircle
        )
    )

    NavigatorTabController(
        navigator = navigator,
        tabDestinations = navigationTabs.map { it.id },
        startDestination = HomeDestination
    ) {
        AppNavigationBar(
            selectedId = it,
            items = navigationTabs,
            onSelected = ::navigateToNavigationBar
        )
    }
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    SampleScreen(
        viewState = SampleViewState(),
        navigator = navigatorPreview(HomeDestination),
        content = {}
    )
}
