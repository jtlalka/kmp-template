package kmp.template.feature.sample.presentation.design

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.feature.sample.presentation.design.SampleDesignIntent.NavigateBackPressed
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateUp

internal class SampleDesignViewModel : MviViewModel<SampleDesignViewState>(SampleDesignViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(screenState = ScreenStateUiModel.Content)
    }

    fun processIntent(intent: SampleDesignIntent) {
        when (intent) {
            is NavigateBackPressed -> onNavigateBackPressed()
        }
    }

    private fun onNavigateBackPressed() {
        emitSideEffect(NavigateUp())
    }
}
