package kmp.template.feature.sample.presentation.environment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.component.base.AppCard
import kmp.template.design.component.base.AppComponentSpacer
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.component.base.AppTitleTextStyle
import kmp.template.design.component.topbar.AppTopCenterBar
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.presentation.environment.SampleEnvironmentIntent.NavigateBackPressed
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.sample_environment_app_id_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_card_header
import kmp_template.module.feature.sample.generated.resources.sample_environment_debug_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_name_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_version_label
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SampleEnvironmentScreen() {

    val viewModel: SampleEnvironmentViewModel = koinViewModel()
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SampleEnvironmentScreen(
        viewState = viewState,
        intent = viewModel::processIntent
    )
}

@Composable
private fun SampleEnvironmentScreen(
    viewState: SampleEnvironmentViewState,
    intent: (SampleEnvironmentIntent) -> Unit
) = AppScaffold(
    topBar = {
        SampleEnvironmentTopBar(
            intent = intent
        )
    },
    content = { contentPadding ->
        SampleEnvironmentContent(
            viewState = viewState,
            contentPadding = contentPadding
        )
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SampleEnvironmentTopBar(
    intent: (SampleEnvironmentIntent) -> Unit
) = AppTopCenterBar(
    title = {
        AppText(text = "Environment")
    },
    navigationIcon = {
        IconButton(onClick = { intent(NavigateBackPressed) }) {
            AppIcon(icon = AppTheme.icons.arrowBack)
        }
    }
)

@Composable
private fun SampleEnvironmentContent(
    viewState: SampleEnvironmentViewState,
    contentPadding: PaddingValues
) = Column(
    verticalArrangement = Arrangement.spacedBy(
        space = AppTheme.dimensions.spaceMd
    ),
    modifier = Modifier
        .padding(contentPadding)
        .fillMaxWidth()
        .verticalScroll(state = rememberScrollState())
        .padding(
            horizontal = AppTheme.dimensions.spaceMd,
            vertical = AppTheme.dimensions.spaceSm
        )
) {
    AppCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        AppTitleTextStyle {
            AppText(stringResource(Res.string.sample_environment_card_header))
            AppComponentSpacer()
        }

        AppText(stringResource(Res.string.sample_environment_name_label, viewState.environmentName))
        AppText(stringResource(Res.string.sample_environment_app_id_label, viewState.applicationId))
        AppText(stringResource(Res.string.sample_environment_version_label, viewState.versionName))
        AppText(stringResource(Res.string.sample_environment_debug_label, viewState.debugLabel))
    }
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    SampleEnvironmentScreen(
        viewState = SampleEnvironmentViewState(
            environmentName = "iOS",
            applicationId = "kmp.template",
            versionName = "1.0.0",
            debugLabel = "1"
        ),
        intent = {}
    )
}
