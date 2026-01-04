package kmp.template.feature.sample.presentation.about

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.foundation.mvi.MviViewModel

internal class SampleAboutViewModel : MviViewModel<SampleAboutViewState>(SampleAboutViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(screenState = ScreenStateUiModel.Content)
    }
}
