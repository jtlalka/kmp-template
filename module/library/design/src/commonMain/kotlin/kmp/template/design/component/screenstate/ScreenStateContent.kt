package kmp.template.design.component.screenstate

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppItemSpacer
import kmp.template.design.component.base.AppOutlinedButton
import kmp.template.design.component.base.AppProgressSpinner
import kmp.template.design.component.base.AppSectionSpacer
import kmp.template.design.component.base.AppText
import kmp.template.design.component.screenstate.ScreenStateUiModel.ErrorState
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading
import kmp.template.design.component.screenstate.ScreenStateUiModel.Content
import kmp.template.design.component.screenstate.ScreenStateUiModel.SuccessState
import kmp.template.design.theme.AppTheme

@Composable
fun ScreenStateContent(
    screenState: ScreenStateUiModel,
    modifier: Modifier = Modifier,
    successCustomIcon: ImageVector? = null,
    errorCustomIcon: ImageVector? = null,
    onFilledButtonClick: () -> Unit = {},
    onOutlineButtonClick: () -> Unit = {}
) = AnimatedContent(
    targetState = screenState,
    transitionSpec = { fadeIn() togetherWith fadeOut() },
    modifier = modifier
) { state ->
    when (state) {
        is Content -> {}
        is Loading -> LoadingScreenState(
            header = state.header,
            message = state.message,
            filledButtonText = state.filledButtonText,
            outlineButtonText = state.outlineButtonText,
            onFilledButtonClick = onFilledButtonClick,
            onOutlineButtonClick = onOutlineButtonClick,
            modifier = Modifier.fillMaxSize()
        )
        is SuccessState -> SuccessScreenState(
            header = state.header,
            message = state.message,
            customIcon = successCustomIcon,
            filledButtonText = state.filledButtonText,
            outlineButtonText = state.outlineButtonText,
            onFilledButtonClick = onFilledButtonClick,
            onOutlineButtonClick = onOutlineButtonClick,
            modifier = Modifier.fillMaxSize()
        )
        is ErrorState -> ErrorScreenState(
            header = state.header,
            message = state.message,
            customIcon = errorCustomIcon,
            filledButtonText = state.filledButtonText,
            outlineButtonText = state.outlineButtonText,
            onFilledButtonClick = onFilledButtonClick,
            onOutlineButtonClick = onOutlineButtonClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun LoadingScreenState(
    header: String,
    message: String,
    modifier: Modifier,
    filledButtonText: String,
    outlineButtonText: String,
    onFilledButtonClick: () -> Unit,
    onOutlineButtonClick: () -> Unit
) = ScreenStateCommonContent(
    header = header,
    message = message,
    modifier = modifier,
    filledButtonText = filledButtonText,
    outlineButtonText = outlineButtonText,
    onFilledButtonClick = onFilledButtonClick,
    onOutlineButtonClick = onOutlineButtonClick
) {
    AppProgressSpinner(
        modifier = Modifier.size(size = AppTheme.dimensions.progressSpinnerSize)
    )
}

@Composable
private fun SuccessScreenState(
    header: String,
    message: String,
    customIcon: ImageVector?,
    filledButtonText: String,
    outlineButtonText: String,
    onFilledButtonClick: () -> Unit,
    onOutlineButtonClick: () -> Unit,
    modifier: Modifier
) = ScreenStateCommonContent(
    header = header,
    message = message,
    modifier = modifier,
    filledButtonText = filledButtonText,
    outlineButtonText = outlineButtonText,
    onFilledButtonClick = onFilledButtonClick,
    onOutlineButtonClick = onOutlineButtonClick
) {
    AppIcon(
        icon = customIcon ?: AppTheme.icons.checkCircle,
        modifier = Modifier.size(size = AppTheme.dimensions.progressSpinnerSize)
    )
}

@Composable
private fun ErrorScreenState(
    header: String,
    message: String,
    customIcon: ImageVector?,
    filledButtonText: String,
    outlineButtonText: String,
    onFilledButtonClick: () -> Unit,
    onOutlineButtonClick: () -> Unit,
    modifier: Modifier
) = ScreenStateCommonContent(
    header = header,
    message = message,
    modifier = modifier,
    filledButtonText = filledButtonText,
    outlineButtonText = outlineButtonText,
    onFilledButtonClick = onFilledButtonClick,
    onOutlineButtonClick = onOutlineButtonClick
) {
    AppIcon(
        icon = customIcon ?: AppTheme.icons.errorCircle,
        tint = AppTheme.colors.error,
        modifier = Modifier.size(size = AppTheme.dimensions.progressSpinnerSize)
    )
}

@Composable
private fun ScreenStateCommonContent(
    header: String,
    message: String,
    modifier: Modifier,
    filledButtonText: String,
    outlineButtonText: String,
    onFilledButtonClick: () -> Unit,
    onOutlineButtonClick: () -> Unit,
    customContent: @Composable ColumnScope.() -> Unit
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = spacedBy(AppTheme.dimensions.spaceSm)
    ) {
        customContent()

        if (header.isNotEmpty() || message.isNotEmpty()) {
            AppItemSpacer()
        }
        if (header.isNotEmpty()) {
            AppText(
                text = header,
                style = AppTheme.typography.display
            )
        }
        if (message.isNotEmpty()) {
            AppText(
                text = message,
                style = AppTheme.typography.bodyMedium
            )
        }
        if (filledButtonText.isNotEmpty() || outlineButtonText.isNotEmpty()) {
            AppSectionSpacer()
        }
        if (filledButtonText.isNotEmpty()) {
            AppFilledButton(
                label = filledButtonText,
                onClick = onFilledButtonClick
            )
        }
        if (outlineButtonText.isNotEmpty()) {
            AppOutlinedButton(
                label = outlineButtonText,
                onClick = onOutlineButtonClick
            )
        }
    }
}

@ScreenPreview
@Composable
private fun LoadingScreenStatePreview() = AppTheme {
    ScreenStateContent(
        screenState = Loading(
            message = "Loading..."
        )
    )
}

@ScreenPreview
@Composable
private fun SuccessScreenStatePreview() = AppTheme {
    ScreenStateContent(
        screenState = SuccessState(
            header = "Hurray!",
            message = "Success message",
            filledButtonText = "Home Screen"
        )
    )
}

@ScreenPreview
@Composable
private fun ErrorScreenStatePreview() = AppTheme {
    ScreenStateContent(
        screenState = ErrorState(
            message = "Something went wrong :(",
            filledButtonText = "Refresh",
            outlineButtonText = "Cancel"
        )
    )
}
