package kmp.template.design.component.base

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme
import kmp.template.design.theme.disableContentAlpha

@Composable
fun AppInput(
    label: String,
    value: String,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    imeAction: ImeAction = ImeAction.Unspecified,
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) = OutlinedTextField(
    label = { Text(label) },
    value = value,
    enabled = enabled,
    readOnly = readOnly,
    singleLine = singleLine,
    textStyle = AppTheme.typography.input,
    shape = AppTheme.shapes.input,
    colors = OutlinedTextFieldDefaults.colors().copy(
        focusedTextColor = AppTheme.colors.onSurface,
        unfocusedTextColor = AppTheme.colors.onSurface,
        disabledTextColor = AppTheme.colors.onSurface.disableContentAlpha(),
        errorTextColor = AppTheme.colors.onSurface,
        focusedIndicatorColor = AppTheme.colors.secondary,
        unfocusedLabelColor = AppTheme.colors.onSurface,
        disabledLabelColor = AppTheme.colors.onSurface.disableContentAlpha(),
    ),
    keyboardOptions = KeyboardOptions(
        imeAction = imeAction,
        keyboardType = keyboardType
    ),
    keyboardActions = keyboardActions,
    visualTransformation = visualTransformation,
    onValueChange = onValueChange,
    modifier = modifier
)

@ComponentPreview
@Composable
private fun AppInputPreview() = AppTheme {
    AppInput(
        label = "Label",
        value = "12",
        onValueChange = {}
    )
}
