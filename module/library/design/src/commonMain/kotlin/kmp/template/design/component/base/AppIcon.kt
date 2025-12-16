package kmp.template.design.component.base

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppIcon(
    icon: DrawableResource,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current
) = Icon(
    painter = painterResource(icon),
    contentDescription = contentDescription,
    modifier = modifier,
    tint = tint
)

@ComponentPreview
@Composable
private fun AppIconPreview() = AppTheme {
    AppIcon(
        icon = AppTheme.icons.home,
        modifier = Modifier.size(size = 64.dp)
    )
}
