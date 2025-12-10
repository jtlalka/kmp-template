package kmp.template.foundation.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <UiAction> SideEffectDispatcher(
    eventsFlow: Flow<UiAction>,
    onEvent: suspend (UiAction) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentOnEvent by rememberUpdatedState(newValue = onEvent)

    LaunchedEffect(eventsFlow) {
        lifecycleOwner.repeatOnLifecycle(STARTED) {
            eventsFlow.collect { event ->
                currentOnEvent(event)
            }
        }
    }
}
