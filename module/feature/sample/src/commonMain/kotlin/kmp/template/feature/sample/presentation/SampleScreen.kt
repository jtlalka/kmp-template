package kmp.template.feature.sample.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp.template.design.component.base.AppButton
import kmp.template.design.component.base.AppCard
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
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
    AppCard(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
            .padding(all = 16.dp)
    ) {
        AppText(stringResource(Res.string.sample_screen_header))
        Spacer(modifier = Modifier.height(AppTheme.dimensions.small))

        AppText(stringResource(Res.string.sample_environment_name_label, viewState.environmentName))
        AppText(stringResource(Res.string.sample_environment_app_id_label, viewState.applicationId))
        AppText(stringResource(Res.string.sample_environment_version_label, viewState.versionName))
        AppText(stringResource(Res.string.sample_environment_debug_label, viewState.debugLabel))

        AppButton(
            label = stringResource(Res.string.sample_environment_screen_header),
            onClick = { intent(EnvironmentPressed) }
        )
    }
}
