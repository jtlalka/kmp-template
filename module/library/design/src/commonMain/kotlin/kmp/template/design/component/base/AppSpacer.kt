package kmp.template.design.component.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme

@Composable
fun AppSectionSpacer(modifier: Modifier = Modifier) = Spacer(
    modifier = modifier.size(size = AppTheme.dimensions.spaceLg)
)

@Composable
fun AppComponentSpacer(modifier: Modifier = Modifier) = Spacer(
    modifier = modifier.size(size = AppTheme.dimensions.spaceMd)
)

@Composable
fun AppItemSpacer(modifier: Modifier = Modifier) = Spacer(
    modifier = modifier.size(size = AppTheme.dimensions.spaceSm)
)

@ComponentPreview
@Composable
private fun AppSpacerPreview() = AppTheme {
    Column {
        AppSectionSpacer(modifier = Modifier.background(AppTheme.colors.primary))
        AppComponentSpacer(modifier = Modifier.background(AppTheme.colors.secondary))
        AppItemSpacer(modifier = Modifier.background(AppTheme.colors.error))
    }
}
