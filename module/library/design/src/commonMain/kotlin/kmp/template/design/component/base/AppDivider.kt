package kmp.template.design.component.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import kmp.template.design.theme.disableContentAlpha

@Composable
fun AppDivider(
    verticalPadding: Dp = 0.dp
) = HorizontalDivider(
    modifier = Modifier.padding(vertical = verticalPadding),
    color = AppTheme.colors.onSurface.disableContentAlpha(),
    thickness = 1.dp
)

@ComponentPreview
@Composable
private fun AppDividerPreview() = AppTheme {
    AppDivider()
}
