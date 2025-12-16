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
import kmp.template.design.component.base.AppButtonRow
import kmp.template.design.component.base.AppCard
import kmp.template.design.component.base.AppComponentSpacer
import kmp.template.design.component.base.AppDisplayTextStyle
import kmp.template.design.component.base.AppDivider
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppFilledIconButton
import kmp.template.design.component.base.AppOutlinedButton
import kmp.template.design.component.base.AppOutlinedIconButton
import kmp.template.design.component.base.AppOutlinedInput
import kmp.template.design.component.base.AppProgressBar
import kmp.template.design.component.base.AppProgressSpinner
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

        AppCard(
            modifier = Modifier.fillMaxWidth()
        ) {
            AppDisplayTextStyle {
                AppText(text = "Basic Design System")
            }
        }

        AppCard(
            modifier = Modifier.fillMaxWidth(),
            itemPadding = AppTheme.dimensions.spaceMd
        ) {
            AppDisplayTextStyle {
                AppText(text = "Fonts")
                AppDivider()
            }
            AppText(text = "Text format: display", style = AppTheme.typography.display)
            AppText(text = "Text format: headline", style = AppTheme.typography.headline)
            AppText(text = "Text format: title", style = AppTheme.typography.title)
            AppText(text = "Text format: body medium", style = AppTheme.typography.bodyMedium)
            AppText(text = "Text format: body small", style = AppTheme.typography.bodySmall)
            AppText(text = "Text format: button", style = AppTheme.typography.button)
            AppText(text = "Text format: input", style = AppTheme.typography.input)
        }

        AppCard(
            modifier = Modifier.fillMaxWidth(),
            itemPadding = AppTheme.dimensions.spaceMd
        ) {
            AppDisplayTextStyle {
                AppText(text = "Buttons")
                AppDivider()
            }
            AppButtonRow {
                AppFilledButton(
                    label = "Button",
                    onClick = {}
                )
                AppFilledButton(
                    label = "Button",
                    enabled = false,
                    onClick = {}
                )
                AppFilledIconButton(
                    icon = AppTheme.icons.arrowBack,
                    onClick = {}
                )
            }
            AppButtonRow {
                AppOutlinedButton(
                    label = "Button",
                    onClick = {}
                )
                AppOutlinedButton(
                    label = "Button",
                    enabled = false,
                    onClick = {}
                )
                AppOutlinedIconButton(
                    icon = AppTheme.icons.arrowBack,
                    onClick = {}
                )
            }
        }

        AppCard(
            modifier = Modifier.fillMaxWidth(),
            itemPadding = AppTheme.dimensions.spaceMd
        ) {
            AppDisplayTextStyle {
                AppText(text = "Inputs")
                AppDivider()
            }
            AppOutlinedInput(
                label = "Empty input",
                value = "",
                onValueChange = {}
            )
            AppOutlinedInput(
                label = "Fulfill input",
                value = "600 500 400",
                onValueChange = {},
                prefix = { AppText(text = "+48") },
                suffix = { AppText(text = "PL") },
            )
            AppOutlinedInput(
                label = "Disabled input",
                value = "",
                enabled = false,
                onValueChange = {}
            )
        }

        AppCard(
            modifier = Modifier.fillMaxWidth(),
            itemPadding = AppTheme.dimensions.spaceMd
        ) {
            AppDisplayTextStyle {
                AppText(text = "Progress")
                AppDivider()
            }

            AppProgressBar()
            AppProgressBar(progress = 0.00f)
            AppProgressBar(progress = 0.25f)
            AppProgressBar(progress = 0.50f)
            AppProgressBar(progress = 1.00f)

            AppButtonRow {
                AppProgressSpinner()
                AppProgressSpinner(progress = 0.00f)
                AppProgressSpinner(progress = 0.25f)
                AppProgressSpinner(progress = 0.50f)
                AppProgressSpinner(progress = 1.00f)
            }
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
