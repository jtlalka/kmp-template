package kmp.template.preferences.internal

import kmp.template.preferences.Preferences
import kmp.template.preferences.db.DataStore
import kmp.template.preferences.exception.InvalidKeyTypeException
import kmp.template.preferences.internal.db.dao.PreferencesDao
import kmp.template.preferences.internal.observer.DataObserver
import kmp.template.preferences.internal.serializer.DataSerializer
import kmp.template.preferences.internal.timer.LocalTimer
import kmp.template.preferences.model.Key
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

internal class PreferencesEditor(
    private val dao: PreferencesDao,
    private val dataSerializer: DataSerializer,
    private val dataObserver: DataObserver,
    private val localTimer: LocalTimer,
    private val dispatcher: CoroutineDispatcher
) : Preferences {

    override fun <T : Any> observe(key: Key<T>): Flow<T?> =
        dataObserver.observeKey(key)

    override suspend fun <T : Any> get(key: Key<T>) = launch {
        loadDataFromDb(key)
    }

    override suspend fun <T : Any> set(key: Key<T>, value: T) = launch {
        storeDataInDb(key, value)
        dataObserver.updateKey(key, value)
    }

    override suspend fun <T : Any> edit(key: Key<T>, block: (T?) -> T) = launch {
        block(loadDataFromDb(key)).let { updatedValue ->
            storeDataInDb(key, updatedValue)
            dataObserver.updateKey(key, updatedValue)
        }
    }

    private suspend fun <T : Any> loadDataFromDb(key: Key<T>): T? {
        val row = dao.selectBy(key.name) ?: return null
        if (key.type != row.type) {
            throw InvalidKeyTypeException(key, row.type)
        }
        return dataSerializer.decode(key.serializer, row.encoded)
    }

    private suspend fun <T : Any> storeDataInDb(key: Key<T>, value: T) {
        val serialized = dataSerializer.encode(key.serializer, value)
        dao.upsert(
            dataStore = DataStore(
                key = key.name,
                type = key.type,
                encoded = serialized,
                updatedAt = localTimer.getCurrentTime()
            )
        )
    }

    override suspend fun <T : Any> hasKey(key: Key<T>) = launch {
        dao.selectBy(key.name)?.type == key.type
    }

    override suspend fun remove(key: Key<*>) = launch {
        dao.delete(key.name)
        dataObserver.updateKey(key, null)
    }

    override suspend fun clear() = launch {
        dao.deleteAll()
        dataObserver.clearValues()
    }

    private suspend fun <T> launch(block: suspend () -> T): T =
        withContext(dispatcher) { block() }
}
