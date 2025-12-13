package kmp.template.feature.sample.presentation

internal sealed interface SampleIntent {

    data object EnvironmentPressed : SampleIntent
}
