package kmp.template.feature.sample.presentation.environment

internal sealed interface SampleEnvironmentIntent {

    data object NavigateBackPressed : SampleEnvironmentIntent
}
