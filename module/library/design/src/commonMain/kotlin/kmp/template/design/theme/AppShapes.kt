package kmp.template.design.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

internal val LocalShapes = staticCompositionLocalOf { AppShapes() }

@Immutable
data class AppShapes(
    val surface: CornerBasedShape = RoundedCornerShape(size = 8.dp),
    val dropdown: CornerBasedShape = RoundedCornerShape(size = 8.dp),
    val card: CornerBasedShape = RoundedCornerShape(size = 8.dp),
    val button: CornerBasedShape = RoundedCornerShape(size = 8.dp),
    val input: CornerBasedShape = RoundedCornerShape(size = 8.dp)
)
