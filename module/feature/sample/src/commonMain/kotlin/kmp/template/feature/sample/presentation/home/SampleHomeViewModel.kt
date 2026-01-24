package kmp.template.feature.sample.presentation.home

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.feature.sample.navigation.SampleRoute.DesignDemoDestination
import kmp.template.feature.sample.navigation.SampleRoute.EnvironmentDemoDestination
import kmp.template.feature.sample.navigation.SampleRoute.NetworkDemoDestination
import kmp.template.feature.sample.navigation.SampleRoute.StorageDemoDestination
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.DesignDemoPressed
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.EnvironmentDemoPressed
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.NetworkDemoPressed
import kmp.template.feature.sample.presentation.home.SampleHomeIntent.StorageDemoPressed
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateTo

internal class SampleHomeViewModel : MviViewModel<SampleHomeViewState>(SampleHomeViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(screenState = ScreenStateUiModel.Content)
    }

    fun onIntent(intent: SampleHomeIntent) {
        when (intent) {
            is DesignDemoPressed -> onDesignDemoPressed()
            is EnvironmentDemoPressed -> onEnvironmentDemoPressed()
            is StorageDemoPressed -> onStorageDemoPressed()
            is NetworkDemoPressed -> onNetworkDemoPressed()
        }
    }

    private fun onDesignDemoPressed() {
        emitSideEffect(NavigateTo(DesignDemoDestination))
    }

    private fun onEnvironmentDemoPressed() {
        emitSideEffect(NavigateTo(EnvironmentDemoDestination))
    }

    private fun onStorageDemoPressed() {
        emitSideEffect(NavigateTo(StorageDemoDestination))
    }

    private fun onNetworkDemoPressed() {
        emitSideEffect(NavigateTo(NetworkDemoDestination))
    }
}
