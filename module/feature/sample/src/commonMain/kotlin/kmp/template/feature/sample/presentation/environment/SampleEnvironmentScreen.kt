package kmp.template.feature.sample.presentation.environment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import kmp.template.foundation.lifecycle.SideEffectDispatcher
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.sample_environment_app_id_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_card_header
import kmp_template.module.feature.sample.generated.resources.sample_environment_debug_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_name_label
import kmp_template.module.feature.sample.generated.resources.sample_environment_screen_header
import kmp_template.module.feature.sample.generated.resources.sample_environment_version_label
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SampleEnvironmentScreen(
    viewModel: SampleEnvironmentViewModel,
    navigator: Navigator
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SideEffectDispatcher(viewModel.sideEffect) {
        when (it) {
            is NavigatorEvent -> navigator.navigate(it)
        }
    }

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        )
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SampleEnvironmentTopBar(
    intent: (SampleEnvironmentIntent) -> Unit
) = AppTopCenterBar(
    title = {
        AppText(text = stringResource(Res.string.sample_environment_screen_header))
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
        SampleEnvironmentCard(
            viewState = viewState,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SampleEnvironmentCard(
    viewState: SampleEnvironmentViewState,
    modifier: Modifier
) = AppCard(
    modifier = modifier
) {
    AppTitleTextStyle {
        AppText(stringResource(Res.string.sample_environment_card_header))
    }
    AppComponentSpacer()

    AppText(stringResource(Res.string.sample_environment_name_label, viewState.environmentName))
    AppText(stringResource(Res.string.sample_environment_app_id_label, viewState.applicationId))
    AppText(stringResource(Res.string.sample_environment_version_label, viewState.versionName))
    AppText(stringResource(Res.string.sample_environment_debug_label, viewState.debugLabel))
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
