package kmp.template.design.component.base

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.extension.disableContainerAlpha
import kmp.template.design.extension.disableContentAlpha
import kmp.template.design.theme.AppTheme

@Composable
fun AppOutlinedInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    isError: Boolean = false,
    supportingText: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    imeAction: ImeAction = ImeAction.Unspecified,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) = OutlinedTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = enabled,
    readOnly = readOnly,
    singleLine = singleLine,
    isError = isError,
    label = { AppText(text = label) },
    supportingText = supportingText,
    prefix = prefix,
    suffix = suffix,
    textStyle = AppTheme.typography.input,
    shape = AppTheme.shapes.input,
    colors = OutlinedTextFieldDefaults.colors(
        // Text
        focusedTextColor = AppTheme.colors.onSurface,
        unfocusedTextColor = AppTheme.colors.onSurface,
        disabledTextColor = AppTheme.colors.onSurface.disableContentAlpha(),
        errorTextColor = AppTheme.colors.onSurface,

        // Container
        focusedContainerColor = AppTheme.colors.surface,
        unfocusedContainerColor = AppTheme.colors.surface,
        disabledContainerColor = AppTheme.colors.surface,
        errorContainerColor = AppTheme.colors.surface,

        // Cursor
        cursorColor = AppTheme.colors.primary,
        errorCursorColor = AppTheme.colors.error,

        // Indicator
        focusedBorderColor = AppTheme.colors.primary,
        unfocusedBorderColor = AppTheme.colors.onSurfaceVariant,
        disabledBorderColor = AppTheme.colors.onSurfaceVariant.disableContainerAlpha(),
        errorBorderColor = AppTheme.colors.error,

        // Label
        focusedLabelColor = AppTheme.colors.primary,
        unfocusedLabelColor = AppTheme.colors.onSurfaceVariant,
        disabledLabelColor = AppTheme.colors.onSurfaceVariant.disableContentAlpha(),
        errorLabelColor = AppTheme.colors.error,

        // Prefix
        focusedPrefixColor = AppTheme.colors.onSurface,
        unfocusedPrefixColor = AppTheme.colors.onSurface,
        disabledPrefixColor = AppTheme.colors.onSurface.disableContentAlpha(),
        errorPrefixColor = AppTheme.colors.error,

        // Suffix
        focusedSuffixColor = AppTheme.colors.onSurface,
        unfocusedSuffixColor = AppTheme.colors.onSurface,
        disabledSuffixColor = AppTheme.colors.onSurface.disableContentAlpha(),
        errorSuffixColor = AppTheme.colors.error
    ),
    keyboardOptions = KeyboardOptions(
        imeAction = imeAction,
        keyboardType = keyboardType
    ),
    keyboardActions = keyboardActions,
    visualTransformation = visualTransformation
)

@ComponentPreview
@Composable
private fun AppOutlinedInputPreview() = AppTheme {
    Column {
        AppOutlinedInput(
            label = "Input label",
            value = "600 500 400",
            onValueChange = {},
            prefix = { AppText(text = "+48") },
            suffix = { AppText(text = "PL") },
        )
        AppOutlinedInput(
            label = "Empty input",
            value = "",
            onValueChange = {}
        )
        AppOutlinedInput(
            label = "Disable input",
            value = "12",
            enabled = false,
            onValueChange = {}
        )
        AppOutlinedInput(
            label = "Empty & disable",
            value = "",
            enabled = false,
            onValueChange = {}
        )
    }
}
