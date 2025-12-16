package kmp.template.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

internal val LocalDimensions = staticCompositionLocalOf { AppDimensions() }

@Immutable
data class AppDimensions(
    val none: Dp = 0.dp,

    val spaceXs: Dp = 4.dp,
    val spaceSm: Dp = 8.dp,
    val spaceMd: Dp = 16.dp,
    val spaceLg: Dp = 24.dp,
    val spaceXl: Dp = 32.dp,

    val componentBorder: Dp = 1.dp,

    val buttonMinSize: Dp = 48.dp,
    val inputMinHeight: Dp = 48.dp,
    val progressBarSize: Dp = 4.dp,
    val progressSpinnerSize: Dp = 64.dp,
)
