package kmp.template.feature.sample.presentation.launch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.component.base.AppCard
import kmp.template.design.component.base.AppComponentSpacer
import kmp.template.design.component.base.AppDisplayTextStyle
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.component.base.AppTitleTextStyle
import kmp.template.design.component.screenstate.ScreenStateContent
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.presentation.launch.SampleLaunchIntent.EnvironmentPressed
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.sample_launch_screen_header
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SampleLaunchScreen() {

    val viewModel: SampleLaunchViewModel = koinViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SampleLaunchScreen(
        viewState = viewState,
        intent = viewModel::processIntent
    )
}

@Composable
private fun SampleLaunchScreen(
    viewState: SampleLaunchViewState,
    intent: (SampleLaunchIntent) -> Unit
) = AppScaffold { paddingValues ->
    SampleLaunchContent(
        intent = intent,
        contentPadding = paddingValues
    )
    ScreenStateContent(
        screenState = viewState.screenState,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun SampleLaunchContent(
    intent: (SampleLaunchIntent) -> Unit,
    contentPadding: PaddingValues,
) = Column(
    verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spaceMd),
    modifier = Modifier
        .padding(contentPadding)
        .fillMaxWidth()
        .verticalScroll(state = rememberScrollState())
        .padding(
            horizontal = AppTheme.dimensions.spaceMd,
            vertical = AppTheme.dimensions.spaceSm
        )
) {
    AppDisplayTextStyle {
        AppText(stringResource(Res.string.sample_launch_screen_header))
    }

    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        AppTitleTextStyle {
            AppText("Features")
            AppComponentSpacer()
        }
        AppFilledButton(
            label = "Design System",
            onClick = { intent(EnvironmentPressed) }
        )
        AppFilledButton(
            label = "Environment",
            onClick = { intent(EnvironmentPressed) }
        )
    }
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    SampleLaunchScreen(
        viewState = SampleLaunchViewState(),
        intent = {}
    )
}
