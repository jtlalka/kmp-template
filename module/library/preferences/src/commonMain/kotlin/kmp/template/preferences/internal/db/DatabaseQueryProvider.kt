package kmp.template.preferences.internal.db

import kmp.template.preferences.db.PreferencesDb
import kmp.template.preferences.db.PreferencesDbQueries

internal class DatabaseQueryProvider(
    private val databaseDriverFactory: DatabaseDriverFactory
) {

    private val database: PreferencesDb by lazy {
        PreferencesDb(driver = databaseDriverFactory.createDriver())
    }

    val preferences: PreferencesDbQueries by lazy {
        database.preferencesDbQueries
    }
}
