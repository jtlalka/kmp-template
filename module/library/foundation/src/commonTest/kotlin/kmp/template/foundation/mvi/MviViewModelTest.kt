package kmp.template.foundation.mvi

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kmp.template.foundation.flow.CompletableFlow.Companion.collect
import kmp.template.foundation.mvi.config.MviConfig
import kmp.template.foundation.mvi.config.MviDefaultConfig
import kotlin.test.Test
import kotlin.test.assertEquals

class MviViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private val config: MviConfig = MviDefaultConfig(launchDispatcher = dispatcher)

    private val tested: MviContainer<String> = MviViewModel(
        initialState = INITIAL_STATE,
        config = config
    )

    @Test
    fun `verify initial state of viewState when container is initialised`() {
        assertEquals(INITIAL_STATE, tested.viewState.value)
    }

    @Test
    fun `mute exception thrown from launch function when launch method is triggered`() = runTest {
        tested.launch { throw Exception() }
    }

    @Test
    fun `provide initialState when transform method is triggered for the first time`() = runTest {
        tested.transform { this.also { assertEquals(INITIAL_STATE, this) } }
    }

    @Test
    fun `update viewState when transform method is triggered with given value`() = runTest {
        val givenValue = NEW_VALUE

        tested.transform { givenValue }

        assertEquals(NEW_VALUE, tested.viewState.value)
    }

    @Test
    fun `collect view state when transform function is triggered`() = runTest(dispatcher) {
        val viewState = tested.viewState.collect(this)

        tested.transform { NEW_VALUE }

        assertEquals(listOf(INITIAL_STATE, NEW_VALUE), viewState.complete())
    }

    @Test
    fun `collect sideEffect when emit side effect function is triggered`() = runTest(dispatcher) {
        val sideEffect = tested.sideEffect.collect(this)

        tested.emitSideEffect(NEW_VALUE)

        assertEquals(listOf(NEW_VALUE), sideEffect.complete())
    }

    @Test
    fun `collect sideEffect emitted before subscription`() = runTest(dispatcher) {
        tested.emitSideEffect(NEW_VALUE)

        val sideEffect = tested.sideEffect.collect(this)

        assertEquals(listOf(NEW_VALUE), sideEffect.complete())
    }

    @Test
    fun `collect multiple sideEffects in order`() = runTest(dispatcher) {
        val sideEffect = tested.sideEffect.collect(this)

        tested.emitSideEffect("first")
        tested.emitSideEffect("second")

        assertEquals(listOf("first", "second"), sideEffect.complete())
    }

    private companion object {
        const val INITIAL_STATE = "initialState"
        const val NEW_VALUE = "newValue"
    }
}
