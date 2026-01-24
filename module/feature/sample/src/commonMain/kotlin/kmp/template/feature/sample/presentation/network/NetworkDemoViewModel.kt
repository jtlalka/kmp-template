package kmp.template.feature.sample.presentation.network

import kmp.template.design.component.screenstate.ScreenStateUiModel.Content
import kmp.template.design.component.screenstate.ScreenStateUiModel.ErrorState
import kmp.template.design.component.screenstate.ScreenStateUiModel.Loading
import kmp.template.feature.sample.presentation.network.NetworkDemoIntent.NavigateBackPressed
import kmp.template.feature.sample.presentation.network.NetworkDemoIntent.RefreshListPressed
import kmp.template.feature.sample.presentation.network.repository.ArtworksRepository
import kmp.template.foundation.extension.runWithMinDuration
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateUp
import kotlinx.coroutines.Job

internal class NetworkDemoViewModel(
    private val artworksRepository: ArtworksRepository
) : MviViewModel<NetworkDemoViewState>(NetworkDemoViewState()) {

    private var refreshJob: Job

    init {
        refreshJob = refreshArtworksList()
    }

    fun onIntent(intent: NetworkDemoIntent) {
        when (intent) {
            is RefreshListPressed -> onRefreshListPressed()
            is NavigateBackPressed -> onNavigateBackPressed()
        }
    }

    private fun onRefreshListPressed() {
        if (refreshJob.isCompleted) {
            refreshJob = refreshArtworksList()
        }
    }

    private fun refreshArtworksList() = launch {
        transform { copy(screenState = LOADING_STATE) }

        runWithMinDuration { artworksRepository.getArtworks() }
            .onSuccess { transform { copy(screenState = Content, artworks = it) } }
            .onFailure { transform { copy(screenState = ERROR_STATE) } }
    }

    private fun onNavigateBackPressed() {
        emitSideEffect(NavigateUp())
    }

    companion object {
        private val LOADING_STATE = Loading(message = "Loading data from server...")
        private val ERROR_STATE = ErrorState(message = "Data not available :(", filledButtonText = "Refresh data")
    }
}
