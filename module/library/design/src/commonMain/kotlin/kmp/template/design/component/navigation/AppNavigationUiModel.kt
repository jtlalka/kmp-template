package kmp.template.design.component.navigation

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.DrawableResource

@Stable
data class AppNavigationUiModel(
    val route: Any,
    val label: String,
    val icon: DrawableResource
)
