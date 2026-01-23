package kmp.template.preferences.internal

import dev.mokkery.MockMode
import dev.mokkery.answering.returns
import dev.mokkery.answering.throws
import dev.mokkery.every
import dev.mokkery.everySuspend
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kmp.template.preferences.Preferences
import kmp.template.preferences.db.DataStore
import kmp.template.preferences.exception.InvalidKeyTypeException
import kmp.template.preferences.exception.NoPreferencesKeyException
import kmp.template.preferences.getOrDefault
import kmp.template.preferences.getOrThrow
import kmp.template.preferences.internal.db.dao.PreferencesDao
import kmp.template.preferences.internal.observer.DataObserver
import kmp.template.preferences.internal.serializer.DataSerializer
import kmp.template.preferences.internal.timer.LocalTimer
import kmp.template.preferences.model.Key.Companion.intKey
import kmp.template.preferences.model.Key.Companion.stringKey
import kmp.template.preferences.model.KeyType
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class PreferencesEditorTest {

    private val preferencesDao = mock<PreferencesDao>(MockMode.autofill)
    private val dataObserver = mock<DataObserver>(MockMode.autofill)
    private val localTimer = mock<LocalTimer>(MockMode.autofill)

    private val tested: Preferences = PreferencesEditor(
        dao = preferencesDao,
        dataSerializer = DataSerializer(),
        dataObserver = dataObserver,
        localTimer = localTimer,
        dispatcher = UnconfinedTestDispatcher()
    )

    @Test
    fun `returns empty shared flow when observe function is called`() = runTest {
        val expectedFlow = MutableSharedFlow<String>()
        everySuspend { dataObserver.observeKey(stringKey("name")) } returns expectedFlow

        val result = tested.observe(stringKey("name"))

        assertEquals(expectedFlow, result)
    }

    @Test
    fun `returns value when get function is called for existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        val result = tested.get(stringKey("name"))

        assertEquals("Alice", result)
    }

    @Test
    fun `returns null value when get function is called for non existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns null

        val result = tested.get(stringKey("name"))

        assertEquals(null, result)
    }

    @Test
    fun `throws exception when get function is called for data with wrong key type`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        assertFailsWith<InvalidKeyTypeException> {
            tested.get(intKey("name"))
        }
    }

    @Test
    fun `returns value when getOrThrow function is called for existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        val result = tested.getOrThrow(stringKey("name"))

        assertEquals("Alice", result)
    }

    @Test
    fun `throws exception when getOrThrow function is called for non existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns null

        assertFailsWith<NoPreferencesKeyException> {
            tested.getOrThrow(stringKey("name"))
        }
    }

    @Test
    fun `throws exception when getOrThrow function is called for data with wrong key type`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        assertFailsWith<InvalidKeyTypeException> {
            tested.getOrThrow(intKey("name"))
        }
    }

    @Test
    fun `returns value when getOrDefault function is called for existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        val result = tested.getOrDefault(stringKey("name"), "John")

        assertEquals("Alice", result)
    }

    @Test
    fun `returns default value when getOrDefault function is called for non existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns null

        val result = tested.getOrDefault(stringKey("name"), "John")

        assertEquals("John", result)
    }

    @Test
    fun `throws exception when getOrDefault function is called for data with wrong key type`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        assertFailsWith<InvalidKeyTypeException> {
            tested.getOrDefault(intKey("name"), 100)
        }
    }

    @Test
    fun `updates data by using preferences dao when set function is called`() = runTest {
        every { localTimer.getCurrentTime() } returns dataStoreModel.updatedAt

        tested.set(stringKey("name"), "Leon")

        verifySuspend { preferencesDao.upsert(dataStoreModel.copy(encoded = """"Leon"""")) }
    }

    @Test
    fun `updates data observer when set function is called`() = runTest {
        every { localTimer.getCurrentTime() } returns dataStoreModel.updatedAt

        tested.set(stringKey("name"), "Leon")

        verifySuspend { dataObserver.updateKey(stringKey("name"), "Leon") }
    }

    @Test
    fun `provides and updates value when edit is called with expected arguments`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel
        every { localTimer.getCurrentTime() } returns dataStoreModel.updatedAt

        tested.edit(stringKey("name")) { "MS. $it" }

        verifySuspend {
            preferencesDao.selectBy("name")
            preferencesDao.upsert(dataStoreModel.copy(encoded = """"MS. Alice""""))
            dataObserver.updateKey(stringKey("name"), "MS. Alice")
        }
    }

    @Test
    fun `throws exception when edit is called for data with wrong key type`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        assertFailsWith<InvalidKeyTypeException> {
            tested.edit(intKey("name")) { 0 }
        }
    }

    @Test
    fun `returns true when hasKey function is called for existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        val result = tested.hasKey(stringKey("name"))

        assertEquals(true, result)
    }

    @Test
    fun `returns false when hasKey function is called for invalid data type`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel.copy(type = KeyType.INT)

        val result = tested.hasKey(stringKey("name"))

        assertEquals(false, result)
    }

    @Test
    fun `returns false when hasKey function is called for non existing data`() = runTest {
        everySuspend { preferencesDao.selectBy("new-name") } returns null

        val result = tested.hasKey(stringKey("new-name"))

        assertEquals(false, result)
    }

    @Test
    fun `removes data by using preferences dao when remove function is called`() = runTest {
        tested.remove(stringKey("name"))

        verifySuspend { preferencesDao.delete("name") }
    }

    @Test
    fun `updates data observer when remove function is called`() = runTest {
        tested.remove(stringKey("name"))

        verifySuspend { dataObserver.updateKey(stringKey("name"), null) }
    }

    @Test
    fun `throws exception when delete function throws exception from DB Dao`() = runTest {
        everySuspend { preferencesDao.delete("name") } throws RuntimeException("Boom!")

        assertFailsWith<RuntimeException> {
            tested.remove(stringKey("name"))
        }
    }

    @Test
    fun `removes all data by using preferences dao when clear function is called`() = runTest {
        tested.clear()

        verifySuspend { preferencesDao.deleteAll() }
    }

    @Test
    fun `updates data observer when clear function is called`() = runTest {
        tested.clear()

        verifySuspend { dataObserver.clearValues() }
    }

    @Test
    fun `throws exception when deleteAll function throws exception from DB Dao`() = runTest {
        everySuspend { preferencesDao.deleteAll() } throws RuntimeException("Boom!")

        assertFailsWith<RuntimeException> {
            tested.clear()
        }
    }

    companion object Companion {
        private val dataStoreModel = DataStore(
            key = "name",
            type = KeyType.STRING,
            encoded = """"Alice"""",
            updatedAt = 123
        )
    }
}
