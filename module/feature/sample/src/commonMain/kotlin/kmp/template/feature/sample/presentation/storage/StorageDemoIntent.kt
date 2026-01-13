package kmp.template.feature.sample.presentation.storage

internal sealed interface StorageDemoIntent {

    data object ComposeScreenLaunched : StorageDemoIntent
    data object IncrementValuePressed : StorageDemoIntent
    data object DecrementValuePressed : StorageDemoIntent
    data object RemoveKeyPressed : StorageDemoIntent
    data object ClearStoragePressed : StorageDemoIntent
    data object NavigateBackPressed : StorageDemoIntent
}
