package kmp.template.design.component.navigation

import org.jetbrains.compose.resources.DrawableResource

data class AppNavigationUiModel(
    val route: Any,
    val label: String,
    val icon: DrawableResource
)
