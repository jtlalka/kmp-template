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

internal val MaterialSymbolsExpandLess: ImageVector =
    ImageVector.Builder(
        name = "expand_less",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(
            fill = SolidColor(Color.Black)
        ) {
            moveTo(480f, 431f)
            lineTo(324f, 587f)
            quadToRelative(-11f, 11f, -28f, 11f)
            reflectiveQuadToRelative(-28f, -11f)
            quadToRelative(-11f, -11f, -11f, -28f)
            reflectiveQuadToRelative(11f, -28f)
            lineToRelative(184f, -184f)
            quadToRelative(6f, -6f, 13f, -8.5f)
            reflectiveQuadToRelative(15f, -2.5f)
            quadToRelative(8f, 0f, 15f, 2.5f)
            reflectiveQuadToRelative(13f, 8.5f)
            lineToRelative(184f, 184f)
            quadToRelative(11f, 11f, 11f, 28f)
            reflectiveQuadToRelative(-11f, 28f)
            quadToRelative(-11f, 11f, -28f, 11f)
            reflectiveQuadToRelative(-28f, -11f)
            lineTo(480f, 431f)
            close()
        }
    }.build()

@ComponentPreview
@Composable
private fun AppIconPreview() = AppTheme {
    AppIcon(icon = MaterialSymbolsExpandLess)
}
