package kmp.template.feature.sample.presentation.design

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
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
import kmp.template.design.component.base.AppComponentSpacer
import kmp.template.design.component.base.AppDisplayTextStyle
import kmp.template.design.component.base.AppDivider
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppFilledIconButton
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppItemSpacer
import kmp.template.design.component.base.AppOutlinedButton
import kmp.template.design.component.base.AppOutlinedIconButton
import kmp.template.design.component.base.AppOutlinedInput
import kmp.template.design.component.base.AppProgressBar
import kmp.template.design.component.base.AppProgressSpinner
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppSectionSpacer
import kmp.template.design.component.base.AppText
import kmp.template.design.component.screenstate.ScreenStateContent
import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.topbar.AppTopCenterBar
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.presentation.design.DesignDemoIntent.NavigateBackPressed
import kmp.template.foundation.lifecycle.SideEffectDispatcher
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.design_demo_buttons_card_header
import kmp_template.module.feature.sample.generated.resources.design_demo_fonts_card_header
import kmp_template.module.feature.sample.generated.resources.design_demo_icons_card_header
import kmp_template.module.feature.sample.generated.resources.design_demo_inputs_card_header
import kmp_template.module.feature.sample.generated.resources.design_demo_progress_card_header
import kmp_template.module.feature.sample.generated.resources.design_demo_screen_header
import kmp_template.module.feature.sample.generated.resources.design_demo_spacers_card_header
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DesignDemoScreen(
    viewModel: DesignDemoViewModel,
    navigator: Navigator
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SideEffectDispatcher(viewModel.sideEffect) {
        when (it) {
            is NavigatorEvent -> navigator.navigate(it)
        }
    }

    DesignDemoScreen(
        viewState = viewState,
        intent = viewModel::onIntent
    )
}

@Composable
private fun DesignDemoScreen(
    viewState: DesignDemoViewState,
    intent: (DesignDemoIntent) -> Unit
) = AppScaffold(
    topBar = {
        DesignDemoTopBar(
            intent = intent
        )
    },
    content = { contentPadding ->
        DesignDemoContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = contentPadding.calculateTopPadding())
        )
        ScreenStateContent(
            screenState = viewState.screenState,
            modifier = Modifier.fillMaxSize()
        )
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DesignDemoTopBar(
    intent: (DesignDemoIntent) -> Unit
) = AppTopCenterBar(
    title = {
        AppText(text = stringResource(Res.string.design_demo_screen_header))
    },
    navigationIcon = {
        IconButton(onClick = { intent(NavigateBackPressed) }) {
            AppIcon(icon = AppTheme.icons.arrowBack)
        }
    }
)

@Composable
private fun DesignDemoContent(
    modifier: Modifier,
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
    item { DesignDemoTypography() }
    item { DesignDemoIcons() }
    item { DesignDemoButtons() }
    item { DesignDemoInputs() }
    item { DesignDemoProgress() }
    item { DesignDemoSpacers() }
}

@Composable
private fun DesignDemoTypography() = DesignDemoCardItem(
    title = stringResource(Res.string.design_demo_fonts_card_header)
) {
    AppText(text = "Text format: display", style = AppTheme.typography.display)
    AppText(text = "Text format: headline", style = AppTheme.typography.headline)
    AppText(text = "Text format: title", style = AppTheme.typography.title)
    AppText(text = "Text format: body medium", style = AppTheme.typography.bodyMedium)
    AppText(text = "Text format: body small", style = AppTheme.typography.bodySmall)
    AppText(text = "Text format: button", style = AppTheme.typography.button)
    AppText(text = "Text format: input", style = AppTheme.typography.input)
}

@Composable
private fun DesignDemoIcons() = DesignDemoCardItem(
    title = stringResource(Res.string.design_demo_icons_card_header)
) {
    AppButtonRow(horizontalAlignment = Alignment.Start) {
        AppIcon(icon = AppTheme.icons.home)
        AppIcon(icon = AppTheme.icons.arrowForward)
        AppIcon(icon = AppTheme.icons.arrowBack)
        AppIcon(icon = AppTheme.icons.expandMore)
        AppIcon(icon = AppTheme.icons.expandLess)
        AppIcon(icon = AppTheme.icons.accountCircle)
        AppIcon(icon = AppTheme.icons.infoCircle)
        AppIcon(icon = AppTheme.icons.checkCircle)
        AppIcon(icon = AppTheme.icons.errorCircle)
    }
}

@Composable
private fun DesignDemoButtons() = DesignDemoCardItem(
    title = stringResource(Res.string.design_demo_buttons_card_header)
) {
    AppButtonRow(horizontalAlignment = Alignment.Start) {
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
            icon = AppTheme.icons.accountCircle,
            onClick = {}
        )
    }
    AppButtonRow(horizontalAlignment = Alignment.Start) {
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
            icon = AppTheme.icons.accountCircle,
            onClick = {}
        )
    }
}

@Composable
private fun DesignDemoInputs() = DesignDemoCardItem(
    title = stringResource(Res.string.design_demo_inputs_card_header)
) {
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

@Composable
private fun DesignDemoProgress() = DesignDemoCardItem(
    title = stringResource(Res.string.design_demo_progress_card_header)
) {
    AppProgressBar()
    AppProgressBar(progress = 0.00f)
    AppProgressBar(progress = 0.25f)
    AppProgressBar(progress = 0.50f)
    AppProgressBar(progress = 1.00f)

    AppButtonRow(horizontalAlignment = Alignment.Start) {
        AppProgressSpinner()
        AppProgressSpinner(progress = 0.00f)
        AppProgressSpinner(progress = 0.25f)
        AppProgressSpinner(progress = 0.50f)
        AppProgressSpinner(progress = 1.00f)
    }
}

@Composable
private fun DesignDemoSpacers() = DesignDemoCardItem(
    title = stringResource(Res.string.design_demo_spacers_card_header)
) {
    AppButtonRow(horizontalAlignment = Alignment.Start) {
        AppSectionSpacer(modifier = Modifier.background(AppTheme.colors.primary))
        AppComponentSpacer(modifier = Modifier.background(AppTheme.colors.secondary))
        AppItemSpacer(modifier = Modifier.background(AppTheme.colors.error))
    }
}

@Composable
private fun DesignDemoCardItem(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) = AppCard(
    modifier = Modifier.fillMaxWidth(),
    itemPadding = AppTheme.dimensions.spaceMd
) {
    AppDisplayTextStyle {
        AppText(text = title)
    }
    AppDivider()
    content(this)
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    DesignDemoScreen(
        viewState = DesignDemoViewState(
            screenState = ScreenStateUiModel.Content
        ),
        intent = {}
    )
}
