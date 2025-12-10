package kmp.template.design.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

internal val LocalShapes = staticCompositionLocalOf { AppShapes() }

@Immutable
data class AppShapes(
    val surface: CornerBasedShape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    val dropdown: CornerBasedShape = RoundedCornerShape(8.dp),
    val card: CornerBasedShape = RoundedCornerShape(8.dp),
    val button: CornerBasedShape = RoundedCornerShape(8.dp),
    val input: CornerBasedShape = RoundedCornerShape(8.dp)
)
