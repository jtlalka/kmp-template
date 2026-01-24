package kmp.template.feature.sample.presentation.network

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.component.base.AppButtonRow
import kmp.template.design.component.base.AppCard
import kmp.template.design.component.base.AppDivider
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppHeadlineTextStyle
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppItemSpacer
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.component.screenstate.ScreenStateContent
import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.topbar.AppTopCenterBar
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.presentation.network.NetworkDemoIntent.NavigateBackPressed
import kmp.template.feature.sample.presentation.network.NetworkDemoIntent.RefreshListPressed
import kmp.template.foundation.lifecycle.SideEffectDispatcher
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.network_demo_card_button
import kmp_template.module.feature.sample.generated.resources.network_demo_card_header
import kmp_template.module.feature.sample.generated.resources.network_demo_screen_header
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun NetworkDemoScreen(
    viewModel: NetworkDemoViewModel,
    navigator: Navigator
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SideEffectDispatcher(viewModel.sideEffect) {
        when (it) {
            is NavigatorEvent -> navigator.navigate(it)
        }
    }

    NetworkDemoScreen(
        viewState = viewState,
        intent = viewModel::onIntent
    )
}

@Composable
private fun NetworkDemoScreen(
    viewState: NetworkDemoViewState,
    intent: (NetworkDemoIntent) -> Unit
) = AppScaffold(
    topBar = {
        NetworkDemoTopBar(
            intent = intent
        )
    },
    content = { contentPadding ->
        if (viewState.screenState is ScreenStateUiModel.Content) {
            NetworkDemoContent(
                viewState = viewState,
                intent = intent,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = contentPadding.calculateTopPadding())
            )
        } else {
            ScreenStateContent(
                screenState = viewState.screenState,
                modifier = Modifier.fillMaxSize(),
                onFilledButtonClick = { intent(RefreshListPressed) }
            )
        }
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NetworkDemoTopBar(
    intent: (NetworkDemoIntent) -> Unit
) = AppTopCenterBar(
    title = {
        AppText(text = stringResource(Res.string.network_demo_screen_header))
    },
    navigationIcon = {
        IconButton(onClick = { intent(NavigateBackPressed) }) {
            AppIcon(icon = AppTheme.icons.arrowBack)
        }
    }
)

@Composable
private fun NetworkDemoContent(
    viewState: NetworkDemoViewState,
    intent: (NetworkDemoIntent) -> Unit,
    modifier: Modifier
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(
        space = AppTheme.dimensions.spaceSm
    ),
    contentPadding = PaddingValues(
        horizontal = AppTheme.dimensions.spaceMd,
        vertical = AppTheme.dimensions.spaceSm
    )
) {
    item {
        NetworkDemoArtworkCard(
            viewState = viewState,
            intent = intent,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun NetworkDemoArtworkCard(
    viewState: NetworkDemoViewState,
    intent: (NetworkDemoIntent) -> Unit,
    modifier: Modifier
) = AppCard(
    modifier = modifier,
    itemPadding = AppTheme.dimensions.spaceMd
) {
    AppHeadlineTextStyle {
        AppText(stringResource(Res.string.network_demo_card_header))
    }
    AppDivider()
    viewState.artworks.forEach { artwork ->
        Column {
            AppText(text = artwork.title)
            AppText(text = artwork.artist)
        }
    }
    AppItemSpacer()
    AppButtonRow(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        AppFilledButton(
            label = stringResource(Res.string.network_demo_card_button),
            onClick = { intent(RefreshListPressed) }
        )
    }
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    NetworkDemoScreen(
        viewState = NetworkDemoViewState(
            screenState = ScreenStateUiModel.Content
        ),
        intent = {}
    )
}
