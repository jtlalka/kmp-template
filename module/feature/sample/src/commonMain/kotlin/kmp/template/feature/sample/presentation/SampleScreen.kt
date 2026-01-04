package kmp.template.feature.sample.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import kmp.template.feature.sample.presentation.about.SampleAboutScreen
import kmp.template.feature.sample.presentation.design.SampleDesignScreen
import kmp.template.feature.sample.presentation.environment.SampleEnvironmentScreen
import kmp.template.feature.sample.presentation.home.SampleHomeScreen
import kmp.template.navigation.Navigator
import kmp.template.navigation.compose.NavigatorDisplay
import kmp.template.navigation.compose.NavigatorTabController
import kmp.template.navigation.compose.navigatorPreview
import kmp.template.navigation.compose.rememberNavigator
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import org.koin.compose.viewmodel.koinViewModel

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
            }
        }
    )

    val navEntryProvider: (key: NavKey) -> NavEntry<NavKey> = remember {
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
    navigator: Navigator,
    homeIcon: ImageVector = AppTheme.icons.home,
    aboutIcon: ImageVector = AppTheme.icons.infoCircle,
) {
    val navigationTabs = remember {
        listOf(
            AppNavigationUiModel(
                id = HomeDestination,
                label = "Home",
                icon = homeIcon
            ),
            AppNavigationUiModel(
                id = AboutDestination,
                label = "About",
                icon = aboutIcon
            )
        )
    }

    NavigatorTabController(
        navigator = navigator,
        tabRoutes = navigationTabs.map { it.id },
        startDestination = HomeDestination,
        fullScreenDestinations = listOf(SampleDesignDestination::class)
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
