package kmp.template.preferences.internal

import kmp.template.preferences.Preferences
import kmp.template.preferences.db.DataStore
import kmp.template.preferences.exception.NoPreferencesKeyException
import kmp.template.preferences.internal.db.dao.PreferencesDao
import kmp.template.preferences.internal.serializer.PrimitivesSerializer
import kmp.template.preferences.internal.serializer.model.SerializedModel
import kmp.template.preferences.internal.serializer.model.SerializedType
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

@OptIn(ExperimentalTime::class)
internal class DbPreferences(
    private val dao: PreferencesDao,
    private val serializer: PrimitivesSerializer,
    private val dispatcher: CoroutineDispatcher
) : Preferences {

    override suspend fun <T : Any> getOrThrow(key: String): T = withContext(dispatcher) {
        val row = dao.selectBy(key) ?: throw NoPreferencesKeyException(key)
        serializer.decode(
            SerializedModel(
                value = row.encoded,
                type = SerializedType.valueOf(row.type)
            )
        )
    }

    override suspend fun <T : Any> getOrDefault(key: String, default: T): T = withContext(dispatcher) {
        val row = dao.selectBy(key) ?: return@withContext default
        serializer.decode(
            SerializedModel(
                value = row.encoded,
                type = SerializedType.valueOf(row.type)
            )
        )
    }

    override suspend fun <T : Any> set(key: String, value: T) = withContext(dispatcher) {
        val serialized = serializer.encode(value)
        val timestamp = Clock.System.now().epochSeconds

        dao.upsert(
            DataStore(
                key = key,
                type = serialized.type.name,
                encoded = serialized.value,
                updatedAt = timestamp
            )
        )
    }

    override suspend fun hasKey(key: String): Boolean = withContext(dispatcher) {
        dao.exists(key)
    }

    override suspend fun remove(key: String) = withContext(dispatcher) {
        dao.delete(key)
    }

    override suspend fun clear() = withContext(dispatcher) {
        dao.deleteAll()
    }
}
