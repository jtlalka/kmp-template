package kmp.template.preferences

import dev.mokkery.MockMode
import dev.mokkery.answering.calls
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verifySuspend
import kmp.template.preferences.db.DataStore
import kmp.template.preferences.exception.NoPreferencesKeyException
import kmp.template.preferences.exception.UnsupportedTypeException
import kmp.template.preferences.internal.DbPreferences
import kmp.template.preferences.internal.db.dao.PreferencesDao
import kmp.template.preferences.internal.serializer.PrimitivesSerializer
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class PreferencesTest {

    private val preferencesDao = mock<PreferencesDao>(MockMode.autofill)

    private val tested: Preferences = DbPreferences(
        dao = preferencesDao,
        serializer = PrimitivesSerializer(),
        dispatcher = UnconfinedTestDispatcher()
    )

    @Test
    fun `returns value when get is called for data stored in DB`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        val result = tested.get("name", String::class)

        assertEquals("Alice", result)
    }

    @Test
    fun `returns null value when get is called for data not stored in DB`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns null

        val result = tested.get("name", String::class)

        assertEquals(null, result)
    }

    @Test
    fun `throws exception when get is called for data with wrong type`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        assertFailsWith<UnsupportedTypeException> {
            tested.get("name", Int::class)
        }
    }

    @Test
    fun `returns value when getOrThrow is called for data stored in DB`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        val result = tested.getOrThrow<String>("name")

        assertEquals("Alice", result)
    }

    @Test
    fun `throws exception when getOrThrow is called for data not stored in DB`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns null

        assertFailsWith<NoPreferencesKeyException> {
            tested.getOrThrow<String>("name")
        }
    }

    @Test
    fun `returns value when getOrDefault is called for data stored in DB`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        val result = tested.getOrDefault("name", "John")

        assertEquals("Alice", result)
    }

    @Test
    fun `returns default value when getOrDefault is called for data not stored in DB`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns null

        val result = tested.getOrDefault("name", "John")

        assertEquals("John", result)
    }

    @Test
    fun `updates data by using preferences dao when set function is called`() = runTest {
        lateinit var captured: DataStore
        everySuspend { preferencesDao.upsert(any()) } calls { captured = it.args[0] as DataStore }

        tested.set("name", "Alice")

        assertEquals(dataStoreModel.key, captured.key)
        assertEquals(dataStoreModel.type, captured.type)
        assertEquals(dataStoreModel.encoded, captured.encoded)
    }

    @Test
    fun `provides and updates value when edit is called with expected arguments`() = runTest {
        everySuspend { preferencesDao.selectBy("name") } returns dataStoreModel

        tested.edit("name", "") { "MS. $it" }

        verifySuspend {
            preferencesDao.selectBy("name")
            preferencesDao.upsert(any())
        }
    }

    @Test
    fun `returns true when hasKey is called for data stored in DB`() = runTest {
        everySuspend { preferencesDao.exists("name") } returns true

        val result = tested.hasKey("name")

        assertEquals(true, result)
    }

    @Test
    fun `returns false when hasKey is called for data not stored in DB`() = runTest {
        everySuspend { preferencesDao.exists("name") } returns false

        val result = tested.hasKey("name")

        assertEquals(false, result)
    }

    @Test
    fun `removes data by using preferences dao when remove function is called`() = runTest {
        tested.remove("name")

        verifySuspend { preferencesDao.delete("name") }
    }

    @Test
    fun `removes all data by using preferences dao when clear function is called`() = runTest {
        tested.clear()

        verifySuspend { preferencesDao.deleteAll() }
    }

    companion object Companion {
        private val dataStoreModel = DataStore(
            key = "name",
            type = "STRING",
            encoded = "Alice",
            updatedAt = 123
        )
    }
}
