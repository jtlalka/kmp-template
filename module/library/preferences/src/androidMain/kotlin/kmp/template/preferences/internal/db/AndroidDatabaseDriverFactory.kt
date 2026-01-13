package kmp.template.preferences.internal.db

import android.content.Context
import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import kmp.template.preferences.db.PreferencesDb

internal class AndroidDatabaseDriverFactory(
    private val context: Context
) : DatabaseDriverFactory {

    override fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = PreferencesDb.Schema.synchronous(),
            context = context,
            name = DatabaseConstants.DB_NAME
        )
}
