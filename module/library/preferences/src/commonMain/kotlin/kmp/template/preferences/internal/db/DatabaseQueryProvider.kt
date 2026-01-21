package kmp.template.preferences.internal.db

import app.cash.sqldelight.EnumColumnAdapter
import kmp.template.preferences.db.DataStore
import kmp.template.preferences.db.PreferencesDb
import kmp.template.preferences.db.PreferencesDbQueries

internal class DatabaseQueryProvider(
    private val databaseDriverFactory: DatabaseDriverFactory
) {

    private val database: PreferencesDb by lazy {
        PreferencesDb(
            driver = databaseDriverFactory.createDriver(),
            dataStoreAdapter = DataStore.Adapter(
                typeAdapter = EnumColumnAdapter()
            )
        )
    }

    val preferences: PreferencesDbQueries by lazy {
        database.preferencesDbQueries
    }
}
