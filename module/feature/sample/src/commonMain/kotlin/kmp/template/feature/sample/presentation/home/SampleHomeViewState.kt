package kmp.template.feature.sample.presentation.home

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading

internal data class SampleHomeViewState(
    val screenState: ScreenStateUiModel = Loading()
)
