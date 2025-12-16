package kmp.template.design.component.base

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme

@Composable
fun AppProgressSpinner(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    trackColor: Color = AppTheme.colors.secondaryContainer
) = CircularProgressIndicator(
    modifier = modifier.defaultMinSize(
        minWidth = AppTheme.dimensions.progressSpinnerSize,
        minHeight = AppTheme.dimensions.progressSpinnerSize
    ),
    color = color,
    trackColor = trackColor,
    strokeWidth = AppTheme.dimensions.progressBarSize,
    strokeCap = ProgressIndicatorDefaults.CircularIndeterminateStrokeCap,
    gapSize = AppTheme.dimensions.progressBarSize
)

@Composable
fun AppProgressSpinner(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    trackColor: Color = AppTheme.colors.secondaryContainer
) = CircularProgressIndicator(
    progress = { progress },
    modifier = modifier.defaultMinSize(
        minWidth = AppTheme.dimensions.progressSpinnerSize,
        minHeight = AppTheme.dimensions.progressSpinnerSize
    ),
    color = color,
    trackColor = trackColor,
    strokeWidth = AppTheme.dimensions.progressBarSize
)

@ComponentPreview
@Composable
private fun AppProgressSpinnerPreview() = AppTheme {
    AppButtonRow {
        AppProgressSpinner()
        AppProgressSpinner(progress = 0.00f)
        AppProgressSpinner(progress = 0.25f)
        AppProgressSpinner(progress = 0.50f)
        AppProgressSpinner(progress = 1.00f)
    }
}
