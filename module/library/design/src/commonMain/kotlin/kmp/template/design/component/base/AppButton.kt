package kmp.template.design.component.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.FlowRowScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.extension.disableContainerAlpha
import kmp.template.design.extension.disableContentAlpha
import kmp.template.design.theme.AppTheme

@Composable
fun AppFilledButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: @Composable (() -> Unit)? = null,
    iconEnd: @Composable (() -> Unit)? = null
) = AppButton(
    label = label,
    onClick = onClick,
    colors = AppButtonDefaults.getFilledColors(),
    border = null,
    modifier = modifier,
    enabled = enabled,
    iconStart = iconStart,
    iconEnd = iconEnd
)

@Composable
fun AppOutlinedButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    iconStart: @Composable (() -> Unit)? = null,
    iconEnd: @Composable (() -> Unit)? = null
) = AppButton(
    label = label,
    onClick = onClick,
    colors = AppButtonDefaults.getOutlinedColors(),
    border = AppButtonDefaults.getOutlinedBorder(enabled),
    modifier = modifier,
    enabled = enabled,
    iconStart = iconStart,
    iconEnd = iconEnd
)

@Composable
fun AppFilledIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    enabled: Boolean = true
) = AppButton(
    label = "",
    onClick = onClick,
    colors = AppButtonDefaults.getFilledColors(),
    border = null,
    modifier = modifier,
    enabled = enabled,
    iconStart = {
        AppIcon(
            icon = icon,
            contentDescription = contentDescription
        )
    },
)

@Composable
fun AppOutlinedIconButton(
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    enabled: Boolean = true
) = AppButton(
    label = "",
    onClick = onClick,
    colors = AppButtonDefaults.getOutlinedColors(),
    border = AppButtonDefaults.getOutlinedBorder(enabled),
    modifier = modifier,
    enabled = enabled,
    iconStart = {
        AppIcon(
            icon = icon,
            contentDescription = contentDescription
        )
    },
)

@Composable
private fun AppButton(
    label: String,
    onClick: () -> Unit,
    colors: ButtonColors,
    border: BorderStroke?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    shape: Shape = AppTheme.shapes.button,
    iconStart: @Composable (() -> Unit)? = null,
    iconEnd: @Composable (() -> Unit)? = null
) = Button(
    onClick = onClick,
    modifier = modifier.defaultMinSize(
        minWidth = AppTheme.dimensions.buttonMinSize,
        minHeight = AppTheme.dimensions.buttonMinSize
    ),
    enabled = enabled,
    shape = shape,
    colors = colors,
    border = border,
    contentPadding = PaddingValues(
        horizontal = AppTheme.dimensions.spaceLg,
        vertical = AppTheme.dimensions.spaceSm
    ),
    content = {
        if (iconStart != null) {
            iconStart()
            if (label.isNotEmpty()) {
                AppItemSpacer()
            }
        }
        if (label.isNotEmpty()) {
            AppText(
                text = label,
                style = AppTheme.typography.button,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        if (iconEnd != null) {
            if (label.isNotEmpty()) {
                AppItemSpacer()
            }
            iconEnd()
        }
    }
)

@Composable
fun AppButtonRow(
    modifier: Modifier = Modifier,
    itemPadding: Dp = AppTheme.dimensions.spaceSm,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable FlowRowScope.() -> Unit
) = FlowRow(
    verticalArrangement = Arrangement.spacedBy(
        alignment = verticalAlignment,
        space = itemPadding
    ),
    horizontalArrangement = Arrangement.spacedBy(
        alignment = horizontalAlignment,
        space = itemPadding
    ),
    modifier = modifier,
    content = content
)

private object AppButtonDefaults {

    @Composable
    fun getFilledColors() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.colors.primary,
        contentColor = AppTheme.colors.onPrimary,
        disabledContainerColor = AppTheme.colors.onSurface.disableContainerAlpha(),
        disabledContentColor = AppTheme.colors.onSurface.disableContentAlpha()
    )

    @Composable
    fun getOutlinedColors() = ButtonDefaults.outlinedButtonColors(
        containerColor = Color.Transparent,
        contentColor = AppTheme.colors.primary,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = AppTheme.colors.onSurface.disableContentAlpha()
    )

    @Composable
    fun getOutlinedBorder(enabled: Boolean) = BorderStroke(
        width = AppTheme.dimensions.componentBorder,
        color = when (enabled) {
            true -> AppTheme.colors.primary
            false -> AppTheme.colors.onSurface.disableContainerAlpha()
        }
    )
}

@ComponentPreview
@Composable
private fun AppButtonPreview() = AppTheme {
    Column {
        AppButtonRow {
            AppFilledButton(
                label = "Button",
                onClick = {}
            )
            AppFilledButton(
                label = "Button",
                enabled = false,
                onClick = {}
            )
            AppFilledIconButton(
                icon = AppTheme.icons.expandLess,
                onClick = {}
            )
        }
        AppButtonRow {
            AppOutlinedButton(
                label = "Button",
                onClick = {}
            )
            AppOutlinedButton(
                label = "Button",
                enabled = false,
                onClick = {}
            )
            AppOutlinedIconButton(
                icon = AppTheme.icons.expandMore,
                onClick = {}
            )
        }
    }
}
