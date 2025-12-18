package kmp.template.feature.sample.presentation.design

import kmp.template.design.component.screenstate.ScreenStateUiModel.Content
import kmp.template.foundation.mvi.MviViewModel

internal class SampleDesignViewModel(
) : MviViewModel<SampleDesignViewState>(SampleDesignViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(screenState = Content)
    }
}
