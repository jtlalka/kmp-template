package kmp.template.design.component.base

import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.extension.disableContainerAlpha
import kmp.template.design.theme.AppTheme

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
    color: Color = AppTheme.colors.onSurfaceVariant.disableContainerAlpha(),
    thickness: Dp = AppTheme.dimensions.componentBorder
) = HorizontalDivider(
    modifier = modifier,
    color = color,
    thickness = thickness
)

@ComponentPreview
@Composable
private fun AppDividerPreview() = AppTheme {
    AppDivider()
}
