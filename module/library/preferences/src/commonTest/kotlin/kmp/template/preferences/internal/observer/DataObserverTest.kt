package kmp.template.preferences.internal.observer

import kmp.template.preferences.model.Key.Companion.intKey
import kmp.template.preferences.model.Key.Companion.longKey
import kmp.template.preferences.model.Key.Companion.stringKey
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

class DataObserverTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = UnconfinedTestDispatcher()

    private val tested = DataObserver()

    @Test
    fun `returns flow with the same instances when observeKey is called twice`() {
        val key = stringKey("KEY")

        val flow1 = tested.observeKey(key)
        val flow2 = tested.observeKey(key)

        assertSame(flow1, flow2)
    }

    @Test
    fun `returns different flow instances when observeKey is called for different key types`() {
        val keyName = "KEY"

        val flow1 = tested.observeKey(stringKey(keyName))
        val flow2 = tested.observeKey(intKey(keyName))

        assertEquals(true, flow1 != flow2)
    }

    @Test
    fun `returns different flow instances when observeKey is called for different key names`() {
        val flow1 = tested.observeKey(stringKey("K1"))
        val flow2 = tested.observeKey(stringKey("K2"))

        assertEquals(true, flow1 != flow2)
    }

    @Test
    fun `returns the same value when updateKey is called on flows observing same key`() = runTest(dispatcher) {
        val key = stringKey("NAME")
        val flow1 = async { tested.observeKey(key).first() }
        val flow2 = async { tested.observeKey(key).first() }

        tested.updateKey(key, "Alice")

        assertEquals("Alice", flow1.await())
        assertEquals("Alice", flow2.await())
    }

    @Test
    fun `returns different value when updateKey is called on flows with different key name`() = runTest(dispatcher) {
        val key1 = stringKey("K1")
        val key2 = stringKey("K2")
        val flow1 = async { tested.observeKey(key1).first() }
        val flow2 = async { tested.observeKey(key2).first() }

        tested.updateKey(key1, "A")
        tested.updateKey(key2, "B")

        assertEquals("A", flow1.await())
        assertEquals("B", flow2.await())
    }

    @Test
    fun `returns different value when updateKey is called on flows with different key type`() = runTest(dispatcher) {
        val keyName = "key"
        val flow1 = async { tested.observeKey(intKey(keyName)).first() }
        val flow2 = async { tested.observeKey(longKey(keyName)).first() }

        tested.updateKey(intKey(keyName), 100)
        tested.updateKey(longKey(keyName), 200L)

        assertEquals(100, flow1.await())
        assertEquals(200L, flow2.await())
    }

    @Test
    fun `sets flows to null when clear function is called`() = runTest(dispatcher) {
        val flow1 = async { tested.observeKey(stringKey("K1")).first() }
        val flow2 = async { tested.observeKey(stringKey("K2")).first() }

        tested.clearValues()

        assertEquals(null, flow1.await())
        assertEquals(null, flow2.await())
    }
}
