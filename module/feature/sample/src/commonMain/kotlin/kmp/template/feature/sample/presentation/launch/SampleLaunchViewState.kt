package kmp.template.feature.sample.presentation.launch

import kmp.template.design.component.screenstate.ScreenStateUiModel

internal data class SampleLaunchViewState(
    val screenState: ScreenStateUiModel = ScreenStateUiModel.Loading()
)
