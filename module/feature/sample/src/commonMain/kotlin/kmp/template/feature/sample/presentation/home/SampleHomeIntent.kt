package kmp.template.feature.sample.presentation.home

internal sealed interface SampleHomeIntent {

    data object DesignDemoPressed : SampleHomeIntent
    data object EnvironmentDemoPressed : SampleHomeIntent
    data object StorageDemoPressed : SampleHomeIntent
    data object NetworkDemoPressed : SampleHomeIntent
}
