package kmp.template.feature.sample.presentation.launch

internal sealed interface SampleLaunchIntent {

    data object DesignSystemPressed : SampleLaunchIntent
    data object EnvironmentPressed : SampleLaunchIntent
}
