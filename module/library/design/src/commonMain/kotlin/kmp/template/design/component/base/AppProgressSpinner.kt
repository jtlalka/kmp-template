package kmp.template.design.component.base

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults.CircularIndeterminateStrokeCap
import androidx.compose.material3.ProgressIndicatorDefaults.CircularStrokeWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme

@Composable
fun AppProgressSpinner(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.secondary,
    trackColor: Color = AppTheme.colors.secondaryContainer
) = CircularProgressIndicator(
    modifier = modifier,
    color = color,
    trackColor = trackColor,
    strokeWidth = CircularStrokeWidth,
    strokeCap = CircularIndeterminateStrokeCap
)

@ComponentPreview
@Composable
private fun AppProgressSpinnerPreview() = AppTheme {
    AppProgressSpinner(modifier = Modifier.size(64.dp))
}
