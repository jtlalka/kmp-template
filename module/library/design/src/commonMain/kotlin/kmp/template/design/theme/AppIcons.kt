package kmp.template.design.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import kmp_template.module.library.design.generated.resources.Res
import kmp_template.module.library.design.generated.resources.ic_arrow_back
import kmp_template.module.library.design.generated.resources.ic_cancel_circle
import kmp_template.module.library.design.generated.resources.ic_check_circle
import kmp_template.module.library.design.generated.resources.ic_error_circle
import kmp_template.module.library.design.generated.resources.ic_home
import kmp_template.module.library.design.generated.resources.ic_info_circle
import org.jetbrains.compose.resources.DrawableResource

internal val LocalIcons = staticCompositionLocalOf { AppIcons() }

@Immutable
data class AppIcons(
    val arrowBack: DrawableResource = Res.drawable.ic_arrow_back,
    val cancel: DrawableResource = Res.drawable.ic_cancel_circle,
    val check: DrawableResource = Res.drawable.ic_check_circle,
    val error: DrawableResource = Res.drawable.ic_error_circle,
    val home: DrawableResource = Res.drawable.ic_home,
    val info: DrawableResource = Res.drawable.ic_info_circle
)
