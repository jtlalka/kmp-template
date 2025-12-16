package kmp.template.design.extension

import androidx.compose.ui.graphics.Color

internal fun Color.disableContainerAlpha() = copy(alpha = 0.12f)

internal fun Color.disableContentAlpha() = copy(alpha = 0.38f)
