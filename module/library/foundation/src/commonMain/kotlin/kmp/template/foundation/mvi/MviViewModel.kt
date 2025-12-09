package kmp.template.foundation.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kmp.template.foundation.mvi.config.MviConfig
import kmp.template.foundation.mvi.config.MviDefaultConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

open class MviViewModel<ViewState>(
    initialState: ViewState,
    private val config: MviConfig = MviDefaultConfig()
) : ViewModel(), MviContainer<ViewState> {

    private val _viewState: MutableStateFlow<ViewState> = MutableStateFlow(initialState)
    override val viewState: StateFlow<ViewState> = _viewState.asStateFlow()

    private val _sideEffect: Channel<Any> = Channel()
    override val sideEffect: Flow<Any> = _sideEffect.receiveAsFlow()

    override fun launch(intent: suspend CoroutineScope.() -> Unit): Job =
        launchJob(intent::invoke)

    override fun transform(transformer: ViewState.() -> ViewState) {
        _viewState.update(transformer::invoke)
    }

    override fun emitSideEffect(sideEffect: Any) {
        launchJob { _sideEffect.send(sideEffect) }
    }

    private fun launchJob(intent: suspend CoroutineScope.() -> Unit): Job =
        viewModelScope.launch(
            context = config.launchDispatcher + config.exceptionHandler,
            block = intent::invoke
        )
}
