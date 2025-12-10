package kmp.template.design.component.base

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import kmp.template.design.annotation.ComponentPreview
import kmp.template.design.theme.AppTheme

@Composable
fun AppText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = LocalTextStyle.current,
    color: Color = LocalContentColor.current,
    textAlign: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1
) = Text(
    text = text,
    modifier = modifier,
    color = color,
    textAlign = textAlign,
    overflow = overflow,
    softWrap = softWrap,
    maxLines = maxLines,
    minLines = minLines,
    style = style
)

@ComponentPreview
@Composable
private fun AppTextPreview() = AppTheme {
    Column {
        AppText(text = "Header", style = AppTheme.typography.header)
        AppText(text = "Sub-header", style = AppTheme.typography.subheader)
        AppText(text = "Body strong", style = AppTheme.typography.bodyStrong)
        AppText(text = "Body regular", style = AppTheme.typography.bodyRegular)
        AppText(text = "Body small", style = AppTheme.typography.bodySmall)
        AppText(text = "Button text", style = AppTheme.typography.button)
        AppText(text = "Input text", style = AppTheme.typography.input)
    }
}
