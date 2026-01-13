package kmp.template.preferences.internal.db.dao

import kmp.template.preferences.db.DataStore

internal interface PreferencesDao {

    suspend fun selectBy(key: String): DataStore?

    suspend fun upsert(dataStore: DataStore)

    suspend fun exists(key: String): Boolean

    suspend fun delete(key: String)

    suspend fun deleteAll()
}
