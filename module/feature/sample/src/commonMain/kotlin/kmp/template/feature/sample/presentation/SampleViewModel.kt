package kmp.template.feature.sample.presentation

import kmp.template.environment.Environment
import kmp.template.feature.sample.presentation.SampleIntent.EnvironmentPressed
import kmp.template.foundation.mvi.MviViewModel

internal class SampleViewModel(
    private val environment: Environment
) : MviViewModel<SampleViewState>(SampleViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = transform {
        copy(
            environmentName = environment.type.name,
            applicationId = environment.applicationId,
            versionName = environment.versionName + " (" + environment.versionCode + ")",
            debugLabel = environment.debug.toString()
        )
    }

    fun processIntent(intent: SampleIntent) {
        when (intent) {
            is EnvironmentPressed -> onEnvironmentPressed()
        }
    }

    private fun onEnvironmentPressed() {
        // it will be implemented with navigation feature
    }
}
