package kmp.template.feature.sample.presentation.storage

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.ClearStoragePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.ComposeScreenLaunched
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.DecrementValuePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.IncrementValuePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.NavigateBackPressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.RemoveKeyPressed
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateUp
import kmp.template.preferences.Preferences

internal class StorageDemoViewModel(
    private val preferences: Preferences
) : MviViewModel<StorageDemoViewState>(StorageDemoViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = launch {
        val viewModelInitCounter = preferences.getOrDefault(SCREEN_INIT_COUNTER_KEY, COUNTER_DEFAULT_VALUE)
            .also { preferences.set(SCREEN_INIT_COUNTER_KEY, it + 1) }

        val userInteractValue = preferences.getOrDefault(USER_INTERACT_VALUE_KEY, INTERACTOR_DEFAULT_VALUE)
        val hasUserInteractKey = preferences.hasKey(USER_INTERACT_VALUE_KEY)

        transform {
            copy(
                screenState = ScreenStateUiModel.Content,
                viewModelInitCounter = viewModelInitCounter,
                userInteractValue = userInteractValue,
                userInteractKeyExist = hasUserInteractKey
            )
        }
    }

    fun onIntent(intent: StorageDemoIntent) {
        when (intent) {
            is ComposeScreenLaunched -> onComposeScreenLaunched()
            is IncrementValuePressed -> onIncrementValuePressed()
            is DecrementValuePressed -> onDecrementValuePressed()
            is RemoveKeyPressed -> onRemoveKeyPressed()
            is ClearStoragePressed -> onClearStoragePressed()
            is NavigateBackPressed -> onNavigateBackPressed()
        }
    }

    private fun onComposeScreenLaunched() = launch {
        val composeLaunchCounter = preferences.getOrDefault(COMPOSE_LAUNCH_COUNTER_KEY, COUNTER_DEFAULT_VALUE)
            .also { preferences.set(COMPOSE_LAUNCH_COUNTER_KEY, it + 1) }

        transform { copy(composeLaunchCounter = composeLaunchCounter) }
    }

    private fun onIncrementValuePressed() = launch {
        updateInteractiveValue { it + 1 }
    }

    private fun onDecrementValuePressed() = launch {
        updateInteractiveValue { it - 1 }
    }

    private suspend fun updateInteractiveValue(predicate: (Long) -> Long) {
        val newValue = predicate(viewState.value.userInteractValue)
        preferences.set(USER_INTERACT_VALUE_KEY, newValue)

        transform {
            copy(
                userInteractValue = newValue,
                userInteractKeyExist = true
            )
        }
    }

    private fun onRemoveKeyPressed() = launch {
        preferences.remove(USER_INTERACT_VALUE_KEY)
        transform {
            copy(
                userInteractValue = INTERACTOR_DEFAULT_VALUE,
                userInteractKeyExist = false
            )
        }
    }

    private fun onClearStoragePressed() = launch {
        preferences.clear()
        transform {
            copy(
                viewModelInitCounter = 0,
                composeLaunchCounter = 0,
                userInteractValue = INTERACTOR_DEFAULT_VALUE,
                userInteractKeyExist = false
            )
        }
    }

    private fun onNavigateBackPressed() {
        emitSideEffect(NavigateUp())
    }

    private companion object {
        const val SCREEN_INIT_COUNTER_KEY = "screenInitCounter"
        const val COMPOSE_LAUNCH_COUNTER_KEY = "composeLaunchCounter"
        const val USER_INTERACT_VALUE_KEY = "userInteractValue"
        const val COUNTER_DEFAULT_VALUE = 1
        const val INTERACTOR_DEFAULT_VALUE = 0L
    }
}
