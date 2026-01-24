package kmp.template.feature.sample.presentation.environment

import kmp.template.environment.Environment
import kmp.template.feature.sample.presentation.environment.EnvironmentDemoIntent.NavigateBackPressed
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateUp

internal class EnvironmentDemoViewModel(
    private val environment: Environment
) : MviViewModel<EnvironmentDemoViewState>(EnvironmentDemoViewState()) {

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

    fun onIntent(intent: EnvironmentDemoIntent) {
        when (intent) {
            is NavigateBackPressed -> onNavigateBackPressed()
        }
    }

    private fun onNavigateBackPressed() {
        emitSideEffect(NavigateUp())
    }
}
