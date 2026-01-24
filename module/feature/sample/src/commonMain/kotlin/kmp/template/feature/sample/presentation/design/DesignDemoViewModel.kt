package kmp.template.feature.sample.presentation.design

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.feature.sample.presentation.design.DesignDemoIntent.NavigateBackPressed
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateUp

internal class DesignDemoViewModel : MviViewModel<DesignDemoViewState>(DesignDemoViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(screenState = ScreenStateUiModel.Content)
    }

    fun onIntent(intent: DesignDemoIntent) {
        when (intent) {
            is NavigateBackPressed -> onNavigateBackPressed()
        }
    }

    private fun onNavigateBackPressed() {
        emitSideEffect(NavigateUp())
    }
}
