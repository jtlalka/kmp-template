package kmp.template.design.component.base

import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme

@Composable
fun AppIcon(
    icon: ImageVector,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) = Icon(
    imageVector = icon,
    contentDescription = contentDescription,
    modifier = modifier,
    tint = tint
)

@ComponentPreview
@Composable
private fun AppIconPreview() = AppTheme {
    AppIcon(icon = AppTheme.icons.home)
}
