package kmp.template.feature.sample.presentation.network

internal sealed interface NetworkDemoIntent {

    data object RefreshListPressed : NetworkDemoIntent
    data object NavigateBackPressed : NetworkDemoIntent
}
