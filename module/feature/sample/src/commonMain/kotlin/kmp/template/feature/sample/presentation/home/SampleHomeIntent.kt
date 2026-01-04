package kmp.template.feature.sample.presentation.home

internal sealed interface SampleHomeIntent {

    data object DesignSystemPressed : SampleHomeIntent
    data object EnvironmentPressed : SampleHomeIntent
}
