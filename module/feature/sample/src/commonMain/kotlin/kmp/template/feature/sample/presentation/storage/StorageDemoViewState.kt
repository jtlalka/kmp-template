package kmp.template.feature.sample.presentation.storage

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading

internal data class StorageDemoViewState(
    val screenState: ScreenStateUiModel = Loading(),
    val viewModelInitCounter: Long = 0L,
    val composeLaunchCounter: Long = 0L,
    val userInteractValue: Int = 0,
    val userInteractKeyExist: Boolean = false
)
