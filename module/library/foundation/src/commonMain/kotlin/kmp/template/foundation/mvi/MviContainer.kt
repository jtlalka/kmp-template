package kmp.template.foundation.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

/**
 * Container which implements MVI (Redux) architecture pattern.
 * ```
 *     +----UI----+                       +--Container--+         +----DOMAIN----+
 *     |          |  -----( intent )--->  |             |  ---->  |  use case    |
 *     | compose  |                       |  ViewModel  |         |  repository  |
 *     |          |  <--( viewState )---  |             |  <----  |  controller  |
 *     +----------+    ( or sideEffect )  +-------------+         +--------------+
 * ```
 * Components:
 *   - Intent - represents interaction from UI ([MviContainer.launch] method)
 *   - Transformer - transforms current UI state to new state ([MviContainer.transform] method)
 *   - ViewState - representation of UI state ([MviContainer.viewState] data flow)
 *   - SideEffect - side effects events data ([MviContainer.sideEffect] data flow)
 */
interface MviContainer<ViewState> {

    val viewState: StateFlow<ViewState>

    val sideEffect: Flow<Any>

    fun launch(intent: suspend CoroutineScope.() -> Unit): Job

    fun transform(transformer: ViewState.() -> ViewState)

    fun emitSideEffect(sideEffect: Any)
}
