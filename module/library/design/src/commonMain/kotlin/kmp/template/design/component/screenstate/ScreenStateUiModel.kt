package kmp.template.design.component.screenstate

import androidx.compose.runtime.Stable

@Stable
sealed class ScreenStateUiModel {

    abstract val header: String
    abstract val message: String
    abstract val filledButtonText: String
    abstract val outlineButtonText: String

    @Stable
    data object Content : ScreenStateUiModel() {
        override val header: String = ""
        override val message: String = ""
        override val filledButtonText: String = ""
        override val outlineButtonText: String = ""
    }

    @Stable
    data class Loading(
        override val header: String = "",
        override val message: String = "",
        override val filledButtonText: String = "",
        override val outlineButtonText: String = ""
    ) : ScreenStateUiModel()

    @Stable
    data class SuccessState(
        override val header: String = "",
        override val message: String = "",
        override val filledButtonText: String = "",
        override val outlineButtonText: String = ""
    ) : ScreenStateUiModel()

    @Stable
    data class ErrorState(
        override val header: String = "",
        override val message: String = "",
        override val filledButtonText: String = "",
        override val outlineButtonText: String = ""
    ) : ScreenStateUiModel()
}
