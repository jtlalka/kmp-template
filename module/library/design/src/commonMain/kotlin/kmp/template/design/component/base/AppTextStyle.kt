package kmp.template.design.component.base

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.runtime.Composable
import kmp.template.design.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun AppDisplayTextStyle(
    content: @Composable () -> Unit
) = ProvideTextStyle(
    value = AppTheme.typography.display,
    content = content
)

@Composable
fun AppHeadlineTextStyle(
    content: @Composable () -> Unit
) = ProvideTextStyle(
    value = AppTheme.typography.headline,
    content = content
)

@Composable
fun AppTitleTextStyle(
    content: @Composable () -> Unit
) = ProvideTextStyle(
    value = AppTheme.typography.title,
    content = content
)

@Composable
fun AppBodyMediumTextStyle(
    content: @Composable () -> Unit
) = ProvideTextStyle(
    value = AppTheme.typography.bodyMedium,
    content = content
)

@Composable
fun AppBodySmallTextStyle(
    content: @Composable () -> Unit
) = ProvideTextStyle(
    value = AppTheme.typography.bodySmall,
    content = content
)

@Preview
@Composable
private fun ComponentPreview() = AppTheme {
    Column {
        AppDisplayTextStyle {
            AppText(text = "Display style")
        }
        AppHeadlineTextStyle {
            AppText(text = "Headline style")
        }
        AppTitleTextStyle {
            AppText(text = "Title style")
        }
        AppBodyMediumTextStyle {
            AppText(text = "Body medium")
        }
        AppBodySmallTextStyle {
            AppText(text = "Body small")
        }
    }
}
