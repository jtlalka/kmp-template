package kmp.template.design.component.topbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppText
import kmp.template.design.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopCenterBar(
    title: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    navigationIcon: @Composable () -> Unit = {},
    actions: @Composable RowScope.() -> Unit = {},
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
    expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
    scrollBehavior: TopAppBarScrollBehavior? = null
) = CenterAlignedTopAppBar(
    title = title,
    modifier = modifier,
    navigationIcon = navigationIcon,
    actions = actions,
    windowInsets = windowInsets,
    expandedHeight = expandedHeight,
    scrollBehavior = scrollBehavior,
    colors = TopAppBarColors(
        containerColor = AppTheme.colors.primary,
        scrolledContainerColor = AppTheme.colors.primaryVariant,
        navigationIconContentColor = AppTheme.colors.onPrimary,
        titleContentColor = AppTheme.colors.onPrimary,
        subtitleContentColor = AppTheme.colors.onPrimary,
        actionIconContentColor = AppTheme.colors.onPrimary
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@ComponentPreview
@Composable
private fun AppTopCenterBarPreview() = AppTheme {
    AppTopCenterBar(
        title = {
            AppText(
                text = "Center Text",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                AppIcon(icon = AppTheme.icons.arrowBack)
            }
        }
    )
}
