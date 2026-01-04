package kmp.template.feature.sample.presentation.environment

import kmp.template.environment.Environment
import kmp.template.feature.sample.presentation.environment.SampleEnvironmentIntent.NavigateBackPressed
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateUp

internal class SampleEnvironmentViewModel(
    private val environment: Environment
) : MviViewModel<SampleEnvironmentViewState>(SampleEnvironmentViewState()) {

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

    fun processIntent(intent: SampleEnvironmentIntent) {
        when (intent) {
            is NavigateBackPressed -> onNavigateBackPressed()
        }
    }

    private fun onNavigateBackPressed() {
        emitSideEffect(NavigateUp())
    }
}
