package kmp.template.design.annotation

import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview(
    name = "1. Portrait mode",
    showBackground = true,
    widthDp = 340,
    heightDp = 720
)
@Preview(
    name = "2. Landscape mode",
    showBackground = true,
    widthDp = 720,
    heightDp = 340
)
annotation class ScreenPreview
