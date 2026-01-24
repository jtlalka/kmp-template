package kmp.template.feature.sample.presentation.design

internal sealed interface DesignDemoIntent {

    data object NavigateBackPressed : DesignDemoIntent
}
