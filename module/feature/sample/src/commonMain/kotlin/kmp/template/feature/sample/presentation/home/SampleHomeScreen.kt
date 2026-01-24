package kmp.template.feature.sample.presentation.home

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.component.base.AppCard
import kmp.template.design.component.base.AppDisplayTextStyle
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppHeadlineTextStyle
import kmp.template.design.component.base.AppItemSpacer
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.component.screenstate.ScreenStateContent
import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.DesignDemoPressed
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.EnvironmentDemoPressed
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.NetworkDemoPressed
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.StorageDemoPressed
import kmp.template.foundation.lifecycle.SideEffectDispatcher
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.sample_home_card_design_label
import kmp_template.module.feature.sample.generated.resources.sample_home_card_environment_label
import kmp_template.module.feature.sample.generated.resources.sample_home_card_header
import kmp_template.module.feature.sample.generated.resources.sample_home_card_network_label
import kmp_template.module.feature.sample.generated.resources.sample_home_card_storage_label
import kmp_template.module.feature.sample.generated.resources.sample_home_screen_header
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SampleHomeScreen(
    viewModel: SampleHomeViewModel,
    navigator: Navigator
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SideEffectDispatcher(viewModel.sideEffect) {
        when (it) {
            is NavigatorEvent -> navigator.navigate(it)
        }
    }

    SampleHomeScreen(
        viewState = viewState,
        intent = viewModel::onIntent
    )
}

@Composable
private fun SampleHomeScreen(
    viewState: SampleHomeViewState,
    intent: (SampleHomeIntent) -> Unit
) = AppScaffold { paddingValues ->
    SampleHomeContent(
        intent = intent,
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    )
    ScreenStateContent(
        screenState = viewState.screenState,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun SampleHomeContent(
    intent: (SampleHomeIntent) -> Unit,
    modifier: Modifier
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = spacedBy(AppTheme.dimensions.spaceSm),
    contentPadding = PaddingValues(AppTheme.dimensions.spaceMd)
) {
    item { SampleHomeHeader() }
    item {
        SampleHomeCard(
            intent = intent
        )
    }
}

@Composable
private fun SampleHomeHeader() = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = AppTheme.dimensions.spaceMd)
) {
    AppDisplayTextStyle {
        AppText(stringResource(Res.string.sample_home_screen_header))
    }
}

@Composable
private fun SampleHomeCard(
    intent: (SampleHomeIntent) -> Unit
) = AppCard(
    itemPadding = AppTheme.dimensions.spaceSm,
    modifier = Modifier.fillMaxWidth()
) {
    AppHeadlineTextStyle {
        AppText(stringResource(Res.string.sample_home_card_header))
    }
    AppItemSpacer()

    AppFilledButton(
        label = stringResource(Res.string.sample_home_card_design_label),
        onClick = { intent(DesignDemoPressed) }
    )
    AppFilledButton(
        label = stringResource(Res.string.sample_home_card_environment_label),
        onClick = { intent(EnvironmentDemoPressed) }
    )
    AppFilledButton(
        label = stringResource(Res.string.sample_home_card_storage_label),
        onClick = { intent(StorageDemoPressed) }
    )
    AppFilledButton(
        label = stringResource(Res.string.sample_home_card_network_label),
        onClick = { intent(NetworkDemoPressed) }
    )
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    SampleHomeScreen(
        viewState = SampleHomeViewState(
            screenState = ScreenStateUiModel.Content
        ),
        intent = {}
    )
}
