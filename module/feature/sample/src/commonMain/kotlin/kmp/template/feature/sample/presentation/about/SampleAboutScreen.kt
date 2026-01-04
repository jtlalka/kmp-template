package kmp.template.feature.sample.presentation.about

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
import kmp.template.design.component.base.AppHeadlineTextStyle
import kmp.template.design.component.base.AppItemSpacer
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.component.screenstate.ScreenStateContent
import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.theme.AppTheme
import kmp.template.environment.EnvironmentType
import kmp.template.foundation.lifecycle.SideEffectDispatcher
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.sample_about_card_header
import kmp_template.module.feature.sample.generated.resources.sample_about_card_info_message
import kmp_template.module.feature.sample.generated.resources.sample_about_card_support_message
import kmp_template.module.feature.sample.generated.resources.sample_about_screen_header
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SampleAboutScreen(
    viewModel: SampleAboutViewModel,
    navigator: Navigator
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SideEffectDispatcher(viewModel.sideEffect) {
        when (it) {
            is NavigatorEvent -> navigator.navigate(it)
        }
    }

    SampleAboutScreen(
        viewState = viewState
    )
}

@Composable
private fun SampleAboutScreen(
    viewState: SampleAboutViewState
) = AppScaffold { paddingValues ->
    SampleAboutContent(
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
private fun SampleAboutContent(
    modifier: Modifier
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = spacedBy(AppTheme.dimensions.spaceSm),
    contentPadding = PaddingValues(AppTheme.dimensions.spaceMd)
) {
    item { SampleAboutHeader() }
    item { SampleAboutCard() }
}

@Composable
private fun SampleAboutHeader() = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = AppTheme.dimensions.spaceMd)
) {
    AppDisplayTextStyle {
        AppText(stringResource(Res.string.sample_about_screen_header))
    }
}

@Composable
private fun SampleAboutCard() = AppCard(
    itemPadding = AppTheme.dimensions.spaceSm,
    modifier = Modifier.fillMaxWidth()
) {
    AppHeadlineTextStyle {
        AppText(stringResource(Res.string.sample_about_card_header))
    }
    AppItemSpacer()

    AppText(stringResource(Res.string.sample_about_card_info_message))
    AppText(stringResource(Res.string.sample_about_card_support_message))

    EnvironmentType.entries.forEach { type ->
        AppText("-> ${type.name}")
    }
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    SampleAboutScreen(
        viewState = SampleAboutViewState(
            screenState = ScreenStateUiModel.Content
        )
    )
}
