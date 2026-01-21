package kmp.template.feature.sample.presentation.storage

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading
import kmp.template.feature.sample.presentation.storage.model.StorageGameUiModel

internal data class StorageDemoViewState(
    val screenState: ScreenStateUiModel = Loading(),
    val viewModelInitCounter: Int = 0,
    val screenLaunchCounter: Long = 0L,
    val userInteractValue: Int = 0,
    val storageGame: StorageGameUiModel = StorageGameUiModel()
)
