package kmp.template.preferences.internal.db

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import kmp.template.preferences.db.PreferencesDb

internal class IosDatabaseDriverFactory : DatabaseDriverFactory {

    override fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = PreferencesDb.Schema.synchronous(),
            name = DatabaseConstants.DB_NAME
        )
}
