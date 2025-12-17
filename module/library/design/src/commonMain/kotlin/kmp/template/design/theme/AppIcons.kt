package kmp.template.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.vector.ImageVector
import kmp.template.design.icon.MaterialSymbolsAccountCircle
import kmp.template.design.icon.MaterialSymbolsArrowBack
import kmp.template.design.icon.MaterialSymbolsArrowForward
import kmp.template.design.icon.MaterialSymbolsCheckCircle
import kmp.template.design.icon.MaterialSymbolsErrorCircle
import kmp.template.design.icon.MaterialSymbolsExpandLess
import kmp.template.design.icon.MaterialSymbolsExpandMore
import kmp.template.design.icon.MaterialSymbolsHome
import kmp.template.design.icon.MaterialSymbolsInfoCircle

internal val LocalIcons = staticCompositionLocalOf { AppIcons() }

@Immutable
data class AppIcons(
    val accountCircle: ImageVector = MaterialSymbolsAccountCircle,
    val arrowBack: ImageVector = MaterialSymbolsArrowBack,
    val arrowForward: ImageVector = MaterialSymbolsArrowForward,
    val checkCircle: ImageVector = MaterialSymbolsCheckCircle,
    val errorCircle: ImageVector = MaterialSymbolsErrorCircle,
    val expandLess: ImageVector = MaterialSymbolsExpandLess,
    val expandMore: ImageVector = MaterialSymbolsExpandMore,
    val home: ImageVector = MaterialSymbolsHome,
    val infoCircle: ImageVector = MaterialSymbolsInfoCircle
)
