package kmp.template.design.component.base

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppProgressBar(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.secondary,
    trackColor: Color = AppTheme.colors.surfaceContainer,
) = LinearProgressIndicator(
    modifier = modifier,
    color = color,
    trackColor = trackColor,
    strokeCap = ProgressIndicatorDefaults.LinearStrokeCap,
    gapSize = ProgressIndicatorDefaults.LinearIndicatorTrackGapSize
)

@ComponentPreview
@Composable
private fun AppProgressBarPreview() = AppTheme {
    AppProgressBar(modifier = Modifier.fillMaxWidth())
}
