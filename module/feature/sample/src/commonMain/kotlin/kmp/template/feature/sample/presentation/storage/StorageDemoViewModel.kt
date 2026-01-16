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
import kmp.template.preferences.edit
import kmp.template.preferences.getOrDefault
import kmp.template.preferences.getOrThrow

internal class StorageDemoViewModel(
    private val preferences: Preferences
) : MviViewModel<StorageDemoViewState>(StorageDemoViewState()) {

    init {
        initViewState()
    }

    private fun initViewState() = launch {
        preferences.edit<Long>(SCREEN_INIT_COUNTER_KEY, COUNTER_DEFAULT_VALUE) { it + 1L }

        val viewModelInitCounter = preferences.getOrThrow<Long>(SCREEN_INIT_COUNTER_KEY)
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
        preferences.edit(COMPOSE_LAUNCH_COUNTER_KEY, COUNTER_DEFAULT_VALUE) { it + 1L }
        val composeLaunchCounter = preferences.getOrThrow<Long>(COMPOSE_LAUNCH_COUNTER_KEY)

        transform { copy(composeLaunchCounter = composeLaunchCounter) }
    }

    private fun onIncrementValuePressed() = launch {
        updateInteractiveValue { it + 1 }
    }

    private fun onDecrementValuePressed() = launch {
        updateInteractiveValue { it - 1 }
    }

    private suspend fun updateInteractiveValue(predicate: (Int) -> Int) {
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
        const val COUNTER_DEFAULT_VALUE = 0L
        const val INTERACTOR_DEFAULT_VALUE = 0
    }
}
