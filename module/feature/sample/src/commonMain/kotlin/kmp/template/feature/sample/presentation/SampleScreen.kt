package kmp.template.feature.sample.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.component.base.AppTitleTextStyle
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.presentation.SampleIntent.EnvironmentPressed
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.sample_environment_app_id_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_debug_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_name_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_screen_header
import kmp_template.module.feature.sample.generated.resources.sample_environment_version_label
import kmp_template.module.feature.sample.generated.resources.sample_screen_header
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SampleScreen() {

    val viewModel: SampleViewModel = koinViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SampleScreen(
        viewState = viewState,
        intent = viewModel::processIntent
    )
}

@Composable
internal fun SampleScreen(
    viewState: SampleViewState,
    intent: (SampleIntent) -> Unit
) = AppScaffold { paddingValues ->
    Column(
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.spaceMd),
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(horizontal = AppTheme.dimensions.spaceMd)
    ) {
        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            AppTitleTextStyle {
                AppText(stringResource(Res.string.sample_screen_header))
                AppComponentSpacer()
            }

            AppText(stringResource(Res.string.sample_environment_name_label, viewState.environmentName))
            AppText(stringResource(Res.string.sample_environment_app_id_label, viewState.applicationId))
            AppText(stringResource(Res.string.sample_environment_version_label, viewState.versionName))
            AppText(stringResource(Res.string.sample_environment_debug_label, viewState.debugLabel))
            AppComponentSpacer()

            AppFilledButton(
                label = stringResource(Res.string.sample_environment_screen_header),
                onClick = { intent(EnvironmentPressed) }
            )
        }
    }
}

@ScreenPreview
@Composable
private fun SampleScreenPreview() = AppTheme {
    SampleScreen(
        viewState = SampleViewState(),
        intent = {}
    )
}
