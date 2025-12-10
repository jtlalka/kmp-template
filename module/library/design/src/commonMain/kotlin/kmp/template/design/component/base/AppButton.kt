package kmp.template.design.component.base

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.component.base.ButtonType.Filled
import kmp.template.design.component.base.ButtonType.Outlined
import kmp.template.design.theme.AppTheme
import kmp.template.design.theme.disableContainerAlpha
import kmp.template.design.theme.disableContentAlpha

@Composable
fun AppButton(
    label: String,
    onClick: () -> Unit,
    type: ButtonType = Filled,
    iconStart: @Composable (() -> Unit)? = null,
    iconEnd: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) = Button(
    onClick = onClick,
    modifier = modifier.defaultMinSize(
        minHeight = AppTheme.dimensions.clickable
    ),
    enabled = enabled,
    elevation = null,
    interactionSource = null,
    shape = AppTheme.shapes.button,
    colors = when (type) {
        Filled -> AppButtonDefaults.getFilledColors()
        Outlined -> AppButtonDefaults.getOutlinedColors()
    },
    border = when (type) {
        Filled -> AppButtonDefaults.getFilledBorder()
        Outlined -> AppButtonDefaults.getOutlinedBorder(enabled)
    },
    contentPadding = PaddingValues(
        horizontal = AppTheme.dimensions.large,
        vertical = AppTheme.dimensions.small
    ),
    content = {
        iconStart?.let { icon ->
            icon()
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = label,
            style = AppTheme.typography.button
        )
        iconEnd?.let { icon ->
            Spacer(modifier = Modifier.width(8.dp))
            icon()
        }
    }
)

enum class ButtonType {
    Filled, Outlined
}

private object AppButtonDefaults {

    @Composable
    fun getFilledColors() = ButtonDefaults.buttonColors(
        containerColor = AppTheme.colors.secondary,
        contentColor = AppTheme.colors.onSecondary,
        disabledContainerColor = AppTheme.colors.onSurface.disableContainerAlpha(),
        disabledContentColor = AppTheme.colors.onSurface.disableContentAlpha()
    )

    @Composable
    fun getOutlinedColors() = ButtonDefaults.outlinedButtonColors(
        containerColor = Color.Transparent,
        contentColor = AppTheme.colors.secondary,
        disabledContainerColor = Color.Transparent,
        disabledContentColor = AppTheme.colors.onSurface.disableContentAlpha()
    )

    @Composable
    fun getFilledBorder() = null

    @Composable
    fun getOutlinedBorder(enabled: Boolean) = BorderStroke(
        width = 1.dp,
        color = when (enabled) {
            true -> AppTheme.colors.secondary
            false -> AppTheme.colors.onSurface.disableContainerAlpha()
        }
    )
}

@ComponentPreview
@Composable
private fun ButtonPreview() = AppTheme {
    Column {
        AppButton(
            label = "App Button",
            type = Filled,
            onClick = {}
        )
        AppButton(
            label = "App Button",
            type = Filled,
            enabled = false,
            onClick = {}
        )
        AppButton(
            label = "App Button",
            type = Outlined,
            onClick = {}
        )
        AppButton(
            label = "App Button",
            type = Outlined,
            enabled = false,
            onClick = {}
        )
    }
}
