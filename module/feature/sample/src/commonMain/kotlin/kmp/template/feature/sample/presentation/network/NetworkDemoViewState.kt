package kmp.template.feature.sample.presentation.network

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading
import kmp.template.feature.sample.presentation.network.model.Artwork

internal data class NetworkDemoViewState(
    val screenState: ScreenStateUiModel = Loading(),
    val artworks: List<Artwork> = emptyList()
)
