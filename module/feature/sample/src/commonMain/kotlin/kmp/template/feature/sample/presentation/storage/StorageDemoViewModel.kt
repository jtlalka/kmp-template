package kmp.template.feature.sample.presentation.storage

import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.ClearStoragePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.ComposeScreenLaunched
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.DecrementValuePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.GameItemPressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.IncrementValuePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.NavigateBackPressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.RemoveGamePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.RemoveKeyPressed
import kmp.template.feature.sample.presentation.storage.model.StorageGameUiModel
import kmp.template.feature.sample.presentation.storage.model.StorageGameUiModel.Companion.EMPTY_PLACE
import kmp.template.foundation.mvi.MviViewModel
import kmp.template.navigation.NavigatorEvent.NavigateUp
import kmp.template.preferences.Preferences
import kmp.template.preferences.getOrDefault
import kmp.template.preferences.getOrThrow
import kmp.template.preferences.model.Key
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map

internal class StorageDemoViewModel(
    private val preferences: Preferences
) : MviViewModel<StorageDemoViewState>(StorageDemoViewState()) {

    init {
        initViewState()
        observeScreenLaunchCounter()
        observeUserValuePreference()
        observeGameObjectPreference()
    }

    private fun initViewState() = launch {
        preferences.edit(VIEW_MODEL_INIT_COUNTER_KEY) { (it ?: VIEW_MODEL_INIT_DEFAULT_VALUE) + 1 }

        val viewModelInitCounter = preferences.getOrThrow(VIEW_MODEL_INIT_COUNTER_KEY)
        val screenLaunchCounter = preferences.getOrDefault(SCREEN_LAUNCH_COUNTER_KEY, SCREEN_LAUNCH_DEFAULT_VALUE)
        val userInteractValue = preferences.getOrDefault(USER_INTERACT_VALUE_KEY, USER_INTERACT_DEFAULT_VALUE)
        val storageGame = preferences.getOrDefault(GAME_SERIALIZED_OBJECT_KEY, StorageGameUiModel())

        transform {
            copy(
                screenState = ScreenStateUiModel.Content,
                viewModelInitCounter = viewModelInitCounter,
                screenLaunchCounter = screenLaunchCounter,
                userInteractValue = userInteractValue,
                storageGame = storageGame
            )
        }
    }

    private fun observeScreenLaunchCounter() = launch {
        preferences
            .observe(key = SCREEN_LAUNCH_COUNTER_KEY)
            .map { it ?: SCREEN_LAUNCH_DEFAULT_VALUE }
            .collectLatest { transform { copy(screenLaunchCounter = it) } }
    }

    private fun observeUserValuePreference() = launch {
        preferences
            .observe(key = USER_INTERACT_VALUE_KEY)
            .map { it ?: USER_INTERACT_DEFAULT_VALUE }
            .collectLatest { transform { copy(userInteractValue = it) } }
    }

    private fun observeGameObjectPreference() = launch {
        preferences
            .observe(key = GAME_SERIALIZED_OBJECT_KEY)
            .map { it ?: StorageGameUiModel() }
            .collectLatest { transform { copy(storageGame = it) } }
    }

    fun onIntent(intent: StorageDemoIntent) {
        when (intent) {
            is ComposeScreenLaunched -> onComposeScreenLaunched()
            is IncrementValuePressed -> onIncrementValuePressed()
            is DecrementValuePressed -> onDecrementValuePressed()
            is RemoveKeyPressed -> onRemoveKeyPressed()
            is RemoveGamePressed -> onRemoveGamePressed()
            is GameItemPressed -> onGameItemPressed(id = intent.id)
            is ClearStoragePressed -> onClearStoragePressed()
            is NavigateBackPressed -> onNavigateBackPressed()
        }
    }

    private fun onComposeScreenLaunched() = launch {
        preferences.edit(SCREEN_LAUNCH_COUNTER_KEY) { (it ?: SCREEN_LAUNCH_DEFAULT_VALUE) + 1L }
    }

    private fun onIncrementValuePressed() = launch {
        preferences.set(USER_INTERACT_VALUE_KEY, viewState.value.userInteractValue + 1)
    }

    private fun onDecrementValuePressed() = launch {
        preferences.set(USER_INTERACT_VALUE_KEY, viewState.value.userInteractValue - 1)
    }

    private fun onRemoveKeyPressed() = launch {
        preferences.remove(USER_INTERACT_VALUE_KEY)
    }

    private fun onRemoveGamePressed() = launch {
        preferences.remove(GAME_SERIALIZED_OBJECT_KEY)
    }

    private fun onGameItemPressed(id: Int) = launch {
        with(viewState.value.storageGame) {
            val playerSign = when (firstPlayerRound) {
                true -> firstPlayerSign
                false -> secondPlayerSign
            }
            val updatedGame = copy(
                firstPlayerRound = !firstPlayerRound,
                gameBoard = gameBoard.mapIndexed { index, item ->
                    when (index) {
                        id if item == EMPTY_PLACE -> playerSign
                        id if item != EMPTY_PLACE -> return@launch
                        else -> item
                    }
                }
            )
            preferences.set(GAME_SERIALIZED_OBJECT_KEY, updatedGame)
        }
    }

    private fun onClearStoragePressed() = launch {
        preferences.clear()
        transform { copy(viewModelInitCounter = VIEW_MODEL_INIT_DEFAULT_VALUE) }
    }

    private fun onNavigateBackPressed() {
        emitSideEffect(NavigateUp())
    }

    private companion object {
        val VIEW_MODEL_INIT_COUNTER_KEY = Key.intKey("viewModelInitCounter")
        val SCREEN_LAUNCH_COUNTER_KEY = Key.longKey("screenLaunchCounter")
        val USER_INTERACT_VALUE_KEY = Key.intKey("userInteractValue")
        val GAME_SERIALIZED_OBJECT_KEY = Key.objectKey("gameSerializedObject", StorageGameUiModel.serializer())

        const val VIEW_MODEL_INIT_DEFAULT_VALUE = 0
        const val SCREEN_LAUNCH_DEFAULT_VALUE = 0L
        const val USER_INTERACT_DEFAULT_VALUE = 0
    }
}
