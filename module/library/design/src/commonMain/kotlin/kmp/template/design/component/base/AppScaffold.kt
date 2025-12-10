package kmp.template.design.component.base

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.component.topbar.AppTopCenterBar
import kmp.template.design.theme.AppTheme

@Composable
fun AppScaffold(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackBarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = AppTheme.colors.background,
    contentColor: Color = AppTheme.colors.onBackground,
    contentWindowInsets: WindowInsets = WindowInsets.systemBars,
    content: @Composable (PaddingValues) -> Unit
) = Scaffold(
    modifier = modifier,
    topBar = topBar,
    bottomBar = bottomBar,
    snackbarHost = snackBarHost,
    floatingActionButton = floatingActionButton,
    floatingActionButtonPosition = floatingActionButtonPosition,
    containerColor = containerColor,
    contentColor = contentColor,
    contentWindowInsets = contentWindowInsets,
    content = content
)

@OptIn(ExperimentalMaterial3Api::class)
@ComponentPreview
@Composable
private fun AppScaffoldPreview() = AppTheme {
    AppScaffold(
        topBar = {
            AppTopCenterBar(
                title = {
                    AppText(
                        text = "App Top Bar",
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
        },
        content = { paddingValues ->
            AppCard(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxWidth()
                    .padding(all = 16.dp)
            ) {
                AppText("App Label")
            }
        }
    )
}
