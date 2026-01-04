package kmp.template.feature.sample.presentation.home

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.feature.sample.navigation.SampleRoute.SampleDesignDestination
import kmp.template.feature.sample.navigation.SampleRoute.SampleEnvironmentDestination
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.DesignSystemPressed
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.EnvironmentPressed
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateTo

internal class SampleHomeViewModel : MviViewModel<SampleHomeViewState>(SampleHomeViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(screenState = ScreenStateUiModel.Content)
    }

    fun processIntent(intent: SampleHomeIntent) {
        when (intent) {
            is DesignSystemPressed -> onDesignSystemPressed()
            is EnvironmentPressed -> onEnvironmentPressed()
        }
    }

    private fun onDesignSystemPressed() {
        emitSideEffect(NavigateTo(SampleDesignDestination))
    }

    private fun onEnvironmentPressed() {
        emitSideEffect(NavigateTo(SampleEnvironmentDestination))
    }
}
