package kmp.template.design.component.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import kmp.template.design.theme.disableContainerAlpha
import kmp.template.design.theme.disableContentAlpha

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier,
    shape = AppTheme.shapes.card,
    colors = CardDefaults.cardColors(
        containerColor = AppTheme.colors.surfaceContainer,
        contentColor = AppTheme.colors.onSurface,
        disabledContentColor = AppTheme.colors.onSurface.disableContentAlpha(),
        disabledContainerColor = AppTheme.colors.surface.disableContainerAlpha()
    ),
    elevation = CardDefaults.cardElevation(),
    border = null
) {
    Column(
        modifier = Modifier.padding(
            paddingValues = PaddingValues(
                horizontal = AppTheme.dimensions.medium,
                vertical = AppTheme.dimensions.medium
            )
        ),
        content = content
    )
}

@ComponentPreview
@Composable
private fun AppCardPreview() = AppTheme {
    Column {
        AppCard {
            AppText(text = "App Card")
            AppText(text = "Card Label")
        }
    }
}
