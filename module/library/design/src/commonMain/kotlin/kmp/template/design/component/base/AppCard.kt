package kmp.template.design.component.base

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.extension.disableContainerAlpha
import kmp.template.design.extension.disableContentAlpha
import kmp.template.design.theme.AppTheme

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    shape: Shape = AppTheme.shapes.card,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    itemPadding: Dp = AppTheme.dimensions.none,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = AppTheme.dimensions.spaceMd,
        vertical = AppTheme.dimensions.spaceMd
    ),
    content: @Composable ColumnScope.() -> Unit
) = Card(
    modifier = modifier,
    shape = shape,
    colors = CardDefaults.cardColors(
        containerColor = AppTheme.colors.surface,
        contentColor = AppTheme.colors.onSurface,
        disabledContentColor = AppTheme.colors.onSurfaceVariant.disableContentAlpha(),
        disabledContainerColor = AppTheme.colors.surfaceVariant.disableContainerAlpha()
    ),
    elevation = CardDefaults.cardElevation(),
    border = null
) {
    Column(
        modifier = Modifier.padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(
            space = itemPadding,
            alignment = verticalAlignment
        ),
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}

@ComponentPreview
@Composable
private fun AppCardPreview() = AppTheme {
    Column {
        AppCard {
            AppTitleTextStyle {
                AppText(text = "App Card Title")
            }
            AppBodyMediumTextStyle {
                AppText(text = "App card body description")
            }
        }
    }
}
