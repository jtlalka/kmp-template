package kmp.template.design.component.navigation

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import kmp.template.design.theme.disableContentAlpha
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppNavigationDropdown(
    expanded: Boolean,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = AppTheme.colors.surface, //surfaceContainer
    containerShape: Shape = AppTheme.shapes.dropdown,
    content: @Composable ColumnScope.() -> Unit
) = DropdownMenu(
    expanded = expanded,
    onDismissRequest = onDismiss,
    modifier = modifier,
    offset = DpOffset(0.dp, 0.dp),
    containerColor = containerColor,
    shape = containerShape,
    tonalElevation = MenuDefaults.TonalElevation,
    shadowElevation = MenuDefaults.ShadowElevation,
    border = null,
    content = content
)

@Composable
fun AppNavigationDropdownItem(
    label: String,
    icon: DrawableResource,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = AppTheme.dimensions.medium,
        vertical = AppTheme.dimensions.extraSmall
    )
) = DropdownMenuItem(
    text = { Text(text = label) },
    leadingIcon = { Icon(painter = painterResource(icon), contentDescription = null) },
    trailingIcon = null,
    modifier = modifier,
    onClick = onClick,
    enabled = true,
    interactionSource = null,
    colors = AppNavigationDropdownDefaults.getDropdownItemColors(),
    contentPadding = contentPadding
)

private object AppNavigationDropdownDefaults {

    @Composable
    fun getDropdownItemColors() = MenuItemColors(
        textColor = AppTheme.colors.onSurface,
        leadingIconColor = AppTheme.colors.onSurface, //onSurfaceVariant
        trailingIconColor = AppTheme.colors.onSurface, //onSurfaceVariant
        disabledTextColor = AppTheme.colors.onSurface.disableContentAlpha(),
        disabledLeadingIconColor = AppTheme.colors.onSurface.disableContentAlpha(),
        disabledTrailingIconColor = AppTheme.colors.onSurface.disableContentAlpha()
    )
}

@ComponentPreview
@Composable
private fun AppNavigationDropdownPreview() = AppTheme {
    AppNavigationDropdown(
        expanded = true,
        onDismiss = {}
    ) {
        AppNavigationDropdownItem(
            label = "Home",
            icon = AppTheme.icons.home,
            onClick = {}
        )
        AppNavigationDropdownItem(
            label = "About",
            icon = AppTheme.icons.info,
            onClick = {}
        )
    }
}
