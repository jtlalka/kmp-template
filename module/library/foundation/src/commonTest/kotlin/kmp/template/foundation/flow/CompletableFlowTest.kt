package kmp.template.foundation.flow

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kmp.template.foundation.flow.CompletableFlow.Companion.collect
import kotlin.test.Test
import kotlin.test.assertEquals

class CompletableFlowTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    @Test
    fun `returns list of states and cancel collection when complete is triggered`() = runTest(dispatcher) {
        val flow = MutableStateFlow(INITIAL_STATE)
        val tested = flow.collect(scope = this)

        flow.emit(NEW_STATE)

        assertEquals(listOf(INITIAL_STATE, NEW_STATE), tested.complete())
    }

    @Test
    fun `returns list with init state when complete is triggered on state flow`() = runTest(dispatcher) {
        val flow = MutableStateFlow(INITIAL_STATE).asStateFlow()

        val tested = flow.collect(scope = this)

        assertEquals(listOf(INITIAL_STATE), tested.complete())
    }

    @Test
    fun `returns empty list of states when complete is triggered on flow`() = runTest(dispatcher) {
        val flow = emptyFlow<String>()

        val tested = flow.collect(scope = this)

        assertEquals(emptyList(), tested.complete())
    }

    @Test
    fun `returns empty list of states when complete is triggered on shared flow`() = runTest(dispatcher) {
        val flow = MutableSharedFlow<String>()

        val tested = flow.collect(scope = this)

        assertEquals(emptyList(), tested.complete())
    }

    @Test
    fun `returns list with init state when complete is triggered multiple times`() = runTest(dispatcher) {
        val flow = MutableStateFlow(INITIAL_STATE)
        val tested = flow.collect(scope = this)

        val result1 = tested.complete()
        val result2 = tested.complete()
        val result3 = tested.complete()

        assertEquals(listOf(INITIAL_STATE), result1)
        assertEquals(listOf(INITIAL_STATE), result2)
        assertEquals(listOf(INITIAL_STATE), result3)
    }

    @Test
    fun `returns list with states when states where emitted before flow was completed`() = runTest(dispatcher) {
        val flow = MutableSharedFlow<String>()
        val tested = flow.collect(scope = this)

        flow.emit(INITIAL_STATE)
        flow.emit(NEW_STATE)

        assertEquals(listOf(INITIAL_STATE, NEW_STATE), tested.complete())
    }

    @Test
    fun `returns empty list when states where emitted after flow was completed`() = runTest(dispatcher) {
        val flow = MutableSharedFlow<String>()
        val tested = flow.collect(scope = this)

        tested.complete()
        flow.emit(INITIAL_STATE)
        flow.emit(NEW_STATE)

        assertEquals(emptyList(), tested.complete())
    }

    private companion object {
        const val INITIAL_STATE = "initialState"
        const val NEW_STATE = "newValue"
    }
}
