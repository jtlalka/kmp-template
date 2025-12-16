package kmp.template.design.component.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme

@Composable
fun AppProgressBar(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    trackColor: Color = AppTheme.colors.secondaryContainer
) = LinearProgressIndicator(
    modifier = modifier.fillMaxWidth(),
    color = color,
    trackColor = trackColor,
    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    gapSize = AppTheme.dimensions.progressBarSize
)

@Composable
fun AppProgressBar(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.primary,
    trackColor: Color = AppTheme.colors.secondaryContainer
) = LinearProgressIndicator(
    modifier = modifier.fillMaxWidth(),
    progress = { progress },
    color = color,
    trackColor = trackColor,
    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    gapSize = AppTheme.dimensions.none,
    drawStopIndicator = {}
)

@ComponentPreview
@Composable
private fun AppProgressBarPreview() = AppTheme {
    Column {
        AppProgressBar()
        AppItemSpacer()
        AppProgressBar(progress = 0.1f)
        AppItemSpacer()
        AppProgressBar(progress = 0.5f)
        AppItemSpacer()
        AppProgressBar(progress = 1.0f)
    }
}
