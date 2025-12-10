package kmp.template.design.component.base

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppIcon(
    icon: DrawableResource,
    contentDescription: String? = null,
    tint: Color = LocalContentColor.current,
    modifier: Modifier = Modifier
) = Icon(
    painter = painterResource(icon),
    contentDescription = contentDescription,
    tint = tint,
    modifier = modifier
)

@ComponentPreview
@Composable
private fun AppIconPreview() = AppTheme {
    AppIcon(
        icon = AppTheme.icons.home,
        modifier = Modifier.size(AppTheme.dimensions.clickable)
    )
}
