package kmp.template.preferences.internal.db.dao

import app.cash.sqldelight.async.coroutines.awaitAsOne
import app.cash.sqldelight.async.coroutines.awaitAsOneOrNull
import kmp.template.preferences.db.DataStore
import kmp.template.preferences.internal.db.DatabaseQueryProvider

internal class PreferencesDbDao(
    private val query: DatabaseQueryProvider
) : PreferencesDao {

    override suspend fun selectBy(key: String): DataStore? =
        query.preferences.select(key).awaitAsOneOrNull()

    override suspend fun upsert(dataStore: DataStore) {
        query.preferences.upsert(dataStore)
    }

    override suspend fun exists(key: String): Boolean =
        query.preferences.exists(key).awaitAsOne()

    override suspend fun delete(key: String) {
        query.preferences.delete(key)
    }

    override suspend fun deleteAll() {
        query.preferences.deleteAll()
    }
}
