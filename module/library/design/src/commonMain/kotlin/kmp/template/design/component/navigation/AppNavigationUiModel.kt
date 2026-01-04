package kmp.template.design.component.navigation

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.vector.ImageVector

@Stable
data class AppNavigationUiModel(
    val id: Any,
    val label: String,
    val icon: ImageVector
)
