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

internal val MaterialSymbolsErrorCircle: ImageVector =
    ImageVector.Builder(
        name = "error_circle_rounded",
        defaultWidth = 24.dp,
        defaultHeight = 24.dp,
        viewportWidth = 960f,
        viewportHeight = 960f
    ).apply {
        path(
            fill = SolidColor(Color.Black)
        ) {
            moveTo(480f, 520f)
            quadToRelative(17f, 0f, 28.52f, -11.52f)
            quadTo(520f, 497f, 520f, 480f)
            verticalLineTo(319f)
            quadToRelative(0f, -17f, -11.48f, -28f)
            quadTo(497f, 280f, 480f, 280f)
            reflectiveQuadToRelative(-28.48f, 11.48f)
            quadTo(440f, 303f, 440f, 320f)
            verticalLineToRelative(161f)
            quadToRelative(0f, 17f, 11.52f, 28f)
            quadToRelative(11.48f, 11f, 28.48f, 11f)
            close()
            moveToRelative(0f, 160f)
            quadToRelative(17f, 0f, 28.52f, -11.52f)
            quadTo(520f, 657f, 520f, 640f)
            reflectiveQuadToRelative(-11.48f, -28.52f)
            quadTo(497f, 600f, 480f, 600f)
            reflectiveQuadToRelative(-28.48f, 11.48f)
            quadTo(440f, 623f, 440f, 640f)
            reflectiveQuadToRelative(11.52f, 28.48f)
            quadTo(463f, 680f, 480f, 680f)
            close()
            moveToRelative(0f, 200f)
            quadToRelative(-83f, 0f, -156f, -31.5f)
            quadToRelative(-73f, -31.48f, -127f, -85.48f)
            quadToRelative(-54f, -54f, -85.48f, -127f)
            quadTo(80f, 563f, 80f, 480f)
            reflectiveQuadToRelative(31.52f, -156f)
            quadToRelative(31.48f, -73f, 85.48f, -127f)
            quadToRelative(54f, -54f, 127f, -85.52f)
            quadTo(397f, 80f, 480f, 80f)
            reflectiveQuadToRelative(156f, 31.48f)
            quadToRelative(73f, 31.52f, 127f, 85.52f)
            quadToRelative(54f, 54f, 85.48f, 127f)
            quadTo(880f, 397f, 880f, 480f)
            reflectiveQuadToRelative(-31.52f, 156f)
            quadToRelative(-31.48f, 73f, -85.48f, 127f)
            quadToRelative(-54f, 54f, -127f, 85.48f)
            quadTo(563f, 880f, 480f, 880f)
            close()
            moveToRelative(0f, -400f)
            close()
            moveToRelative(0f, 320f)
            quadToRelative(133f, 0f, 226.52f, -93.48f)
            quadTo(800f, 613f, 800f, 480f)
            reflectiveQuadToRelative(-93.48f, -226.52f)
            quadTo(613f, 160f, 480f, 160f)
            reflectiveQuadTo(253.52f, 253.48f)
            quadTo(160f, 347f, 160f, 480f)
            reflectiveQuadToRelative(93.48f, 226.52f)
            quadTo(347f, 800f, 480f, 800f)
            close()
        }
    }.build()

@ComponentPreview
@Composable
private fun AppIconPreview() = AppTheme {
    AppIcon(icon = MaterialSymbolsErrorCircle)
}
