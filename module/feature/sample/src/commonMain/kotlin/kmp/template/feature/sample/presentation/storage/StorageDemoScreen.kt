package kmp.template.feature.sample.presentation.storage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kmp.template.design.annotation.ScreenPreview
import kmp.template.design.component.base.AppBodyMediumTextStyle
import kmp.template.design.component.base.AppButtonRow
import kmp.template.design.component.base.AppCard
import kmp.template.design.component.base.AppComponentSpacer
import kmp.template.design.component.base.AppFilledButton
import kmp.template.design.component.base.AppIcon
import kmp.template.design.component.base.AppOutlinedButton
import kmp.template.design.component.base.AppScaffold
import kmp.template.design.component.base.AppText
import kmp.template.design.component.base.AppTitleTextStyle
import kmp.template.design.component.screenstate.ScreenStateContent
import kmp.template.design.component.screenstate.ScreenStateUiModel
import kmp.template.design.component.topbar.AppTopCenterBar
import kmp.template.design.theme.AppTheme
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.ClearStoragePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.ComposeScreenLaunched
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.DecrementValuePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.GameItemPressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.IncrementValuePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.NavigateBackPressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.RemoveGamePressed
import kmp.template.feature.sample.presentation.storage.StorageDemoIntent.RemoveKeyPressed
import kmp.template.feature.sample.presentation.storage.model.StorageGameUiModel
import kmp.template.foundation.lifecycle.SideEffectDispatcher
import kmp.template.navigation.Navigator
import kmp.template.navigation.NavigatorEvent
import kmp_template.module.feature.sample.generated.resources.Res
import kmp_template.module.feature.sample.generated.resources.storage_demo_clear_storage_button
import kmp_template.module.feature.sample.generated.resources.storage_demo_clear_storage_header
import kmp_template.module.feature.sample.generated.resources.storage_demo_counter_card_header
import kmp_template.module.feature.sample.generated.resources.storage_demo_counter_card_screen_label
import kmp_template.module.feature.sample.generated.resources.storage_demo_counter_card_viewmodel_label
import kmp_template.module.feature.sample.generated.resources.storage_demo_game_card_button
import kmp_template.module.feature.sample.generated.resources.storage_demo_game_card_header
import kmp_template.module.feature.sample.generated.resources.storage_demo_game_card_message
import kmp_template.module.feature.sample.generated.resources.storage_demo_game_card_player_info
import kmp_template.module.feature.sample.generated.resources.storage_demo_value_decrement_button
import kmp_template.module.feature.sample.generated.resources.storage_demo_value_header
import kmp_template.module.feature.sample.generated.resources.storage_demo_value_increment_button
import kmp_template.module.feature.sample.generated.resources.storage_demo_value_remove_button
import kmp_template.module.feature.sample.generated.resources.storage_demo_value_value_label
import kmp_template.module.feature.sample.generated.resources.storage_demo_screen_header
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun StorageDemoScreen(
    viewModel: StorageDemoViewModel,
    navigator: Navigator
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    SideEffectDispatcher(viewModel.sideEffect) {
        when (it) {
            is NavigatorEvent -> navigator.navigate(it)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.onIntent(ComposeScreenLaunched)
    }

    StorageDemoScreen(
        viewState = viewState,
        intent = viewModel::onIntent
    )
}

@Composable
private fun StorageDemoScreen(
    viewState: StorageDemoViewState,
    intent: (StorageDemoIntent) -> Unit
) = AppScaffold(
    topBar = {
        StorageDemoTopBar(
            intent = intent
        )
    },
    content = { contentPadding ->
        StorageDemoContent(
            viewState = viewState,
            intent = intent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding)
        )
        ScreenStateContent(
            screenState = viewState.screenState,
            modifier = Modifier.fillMaxSize()
        )
    }
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StorageDemoTopBar(
    intent: (StorageDemoIntent) -> Unit
) = AppTopCenterBar(
    title = {
        AppText(text = stringResource(Res.string.storage_demo_screen_header))
    },
    navigationIcon = {
        IconButton(onClick = { intent(NavigateBackPressed) }) {
            AppIcon(icon = AppTheme.icons.arrowBack)
        }
    }
)

@Composable
private fun StorageDemoContent(
    viewState: StorageDemoViewState,
    intent: (StorageDemoIntent) -> Unit,
    modifier: Modifier
) = LazyColumn(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(
        space = AppTheme.dimensions.spaceSm
    ),
    contentPadding = PaddingValues(
        horizontal = AppTheme.dimensions.spaceMd,
        vertical = AppTheme.dimensions.spaceSm
    )
) {
    item {
        StorageDemoAppCounterCard(
            viewState = viewState,
            modifier = Modifier.fillMaxWidth()
        )
    }
    item {
        StorageDemoUserValueCard(
            viewState = viewState,
            intent = intent,
            modifier = Modifier.fillMaxWidth()
        )
    }
    item {
        StorageDemoGameCard(
            storageGame = viewState.storageGame,
            intent = intent,
            modifier = Modifier.fillMaxWidth()
        )
    }
    item {
        StorageDemoClearCard(
            intent = intent,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun StorageDemoAppCounterCard(
    viewState: StorageDemoViewState,
    modifier: Modifier
) = AppCard(
    modifier = modifier,
    itemPadding = AppTheme.dimensions.spaceMd
) {
    AppTitleTextStyle {
        AppText(stringResource(Res.string.storage_demo_counter_card_header))
    }
    Column {
        AppBodyMediumTextStyle {
            AppText(stringResource(Res.string.storage_demo_counter_card_viewmodel_label, viewState.viewModelInitCounter))
            AppText(stringResource(Res.string.storage_demo_counter_card_screen_label, viewState.screenLaunchCounter))
        }
    }
}

@Composable
private fun StorageDemoUserValueCard(
    viewState: StorageDemoViewState,
    intent: (StorageDemoIntent) -> Unit,
    modifier: Modifier
) = AppCard(
    modifier = modifier,
    itemPadding = AppTheme.dimensions.spaceMd
) {
    AppTitleTextStyle {
        AppText(stringResource(Res.string.storage_demo_value_header))
    }
    AppBodyMediumTextStyle {
        AppText(stringResource(Res.string.storage_demo_value_value_label, viewState.userInteractValue))
    }
    AppButtonRow(horizontalAlignment = Alignment.Start) {
        AppFilledButton(
            label = stringResource(Res.string.storage_demo_value_increment_button),
            onClick = { intent(IncrementValuePressed) }
        )
        AppFilledButton(
            label = stringResource(Res.string.storage_demo_value_decrement_button),
            onClick = { intent(DecrementValuePressed) }
        )
        AppFilledButton(
            label = stringResource(Res.string.storage_demo_value_remove_button),
            onClick = { intent(RemoveKeyPressed) }
        )
    }
}

@Composable
private fun StorageDemoGameCard(
    storageGame: StorageGameUiModel,
    intent: (StorageDemoIntent) -> Unit,
    modifier: Modifier
) = AppCard(
    modifier = modifier,
    itemPadding = AppTheme.dimensions.spaceMd
) {
    AppTitleTextStyle {
        AppText(stringResource(Res.string.storage_demo_game_card_header))
    }
    AppBodyMediumTextStyle {
        AppText(stringResource(Res.string.storage_demo_game_card_message))
        if (storageGame.firstPlayerRound) {
            AppText(stringResource(Res.string.storage_demo_game_card_player_info, storageGame.firstPlayerSign))
        } else {
            AppText(stringResource(Res.string.storage_demo_game_card_player_info, storageGame.secondPlayerSign))
        }
    }
    AppButtonRow(
        horizontalAlignment = Alignment.Start,
        horizontalItemLimit = 3
    ) {
        storageGame.gameBoard.forEachIndexed { index, item ->
            AppOutlinedButton(
                label = item,
                modifier = Modifier.size(size = 64.dp),
                onClick = { intent(GameItemPressed(index)) }
            )
        }
    }
    AppFilledButton(
        label = stringResource(Res.string.storage_demo_game_card_button),
        onClick = { intent(RemoveGamePressed) }
    )
}

@Composable
private fun StorageDemoClearCard(
    intent: (StorageDemoIntent) -> Unit,
    modifier: Modifier
) = AppCard(
    modifier = modifier,
    itemPadding = AppTheme.dimensions.spaceMd
) {
    AppTitleTextStyle {
        AppText(stringResource(Res.string.storage_demo_clear_storage_header))
    }
    AppOutlinedButton(
        label = stringResource(Res.string.storage_demo_clear_storage_button),
        onClick = { intent(ClearStoragePressed) }
    )
}

@ScreenPreview
@Composable
private fun ScreenPreview() = AppTheme {
    StorageDemoScreen(
        viewState = StorageDemoViewState(
            screenState = ScreenStateUiModel.Content,
            storageGame = StorageGameUiModel()
        ),
        intent = {}
    )
}
