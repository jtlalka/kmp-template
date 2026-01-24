package kmp.template.feature.sample.presentation.design

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading

internal data class DesignDemoViewState(
    val screenState: ScreenStateUiModel = Loading()
)
