package kmp.template.feature.sample.presentation.about

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading

internal data class SampleAboutViewState(
    val screenState: ScreenStateUiModel = Loading()
)
