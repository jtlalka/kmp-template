package kmp.template.design.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.component.base.AppIcon
import kmp.template.design.theme.AppTheme

internal val MaterialSymbolsExpandMore: ImageVector =
    ImageVector.Builder(
        name = "expand_more",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(
            fill = SolidColor(Color.Black)
        ) {
            moveTo(480f, 598f)
            quadToRelative(-8f, 0f, -15f, -2.5f)
            reflectiveQuadToRelative(-13f, -8.5f)
            lineTo(268f, 403f)
            quadToRelative(-11f, -11f, -11f, -28f)
            reflectiveQuadToRelative(11f, -28f)
            quadToRelative(11f, -11f, 28f, -11f)
            reflectiveQuadToRelative(28f, 11f)
            lineToRelative(156f, 156f)
            lineToRelative(156f, -156f)
            quadToRelative(11f, -11f, 28f, -11f)
            reflectiveQuadToRelative(28f, 11f)
            quadToRelative(11f, 11f, 11f, 28f)
            reflectiveQuadToRelative(-11f, 28f)
            lineTo(508f, 587f)
            quadToRelative(-6f, 6f, -13f, 8.5f)
            reflectiveQuadToRelative(-15f, 2.5f)
            close()
        }
    }.build()

@ComponentPreview
@Composable
private fun AppIconPreview() = AppTheme {
    AppIcon(icon = MaterialSymbolsExpandMore)
}
