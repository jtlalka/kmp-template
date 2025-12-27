package kmp.template.feature.sample.presentation.launch

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.feature.sample.presentation.launch.SampleLaunchIntent.DesignSystemPressed
import kmp.template.feature.sample.presentation.launch.SampleLaunchIntent.EnvironmentPressed
import kmp.template.foundation.mvi.MviViewModel

internal class SampleLaunchViewModel(
) : MviViewModel<SampleLaunchViewState>(SampleLaunchViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(
            screenState = ScreenStateUiModel.Content
        )
    }

    fun processIntent(intent: SampleLaunchIntent) {
        when (intent) {
            is DesignSystemPressed -> onDesignSystemPressed()
            is EnvironmentPressed -> onEnvironmentPressed()
        }
    }

    private fun onDesignSystemPressed() {
        // it will be implemented with navigation feature
    }

    private fun onEnvironmentPressed() {
        // it will be implemented with navigation feature
    }
}
