package kmp.template.feature.sample.presentation.environment

internal sealed interface EnvironmentDemoIntent {

    data object NavigateBackPressed : EnvironmentDemoIntent
}
