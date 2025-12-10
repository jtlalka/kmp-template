package kmp.template.design.theme

import androidx.compose.ui.graphics.Color

internal object AppColorPalette {

    // content
    val tealDark = Color(0xFF013287)
    val tealLight = Color(0xFF4B40E0)
    val violetDark = Color(0xFF5D3573)
    val violetLight = Color(0xFFAD6EBE)

    // surface
    val black = Color(0xFF121212)
    val grayDark = Color(0xFF424242)
    val grayLight = Color(0x56ECEFF1)
    val white = Color(0xFFFFFFFF)

    // error
    val redDark = Color(0xFFB00020)
    val redLight = Color(0xFFDE283A)
}

internal fun Color.colorVariantAlpha() = copy(alpha = 0.70f)
internal fun Color.colorContainerAlpha() = copy(alpha = 0.45f)

internal fun Color.disableContainerAlpha() = copy(alpha = 0.12f)
internal fun Color.disableContentAlpha() = copy(alpha = 0.38f)
