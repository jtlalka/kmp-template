package kmp.template.feature.sample.presentation.storage

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading

internal data class StorageDemoViewState(
    val screenState: ScreenStateUiModel = Loading(),
    val viewModelInitCounter: Int = 0,
    val composeLaunchCounter: Int = 0,
    val userInteractValue: Long = 0L,
    val userInteractKeyExist: Boolean = false
)
