package kmp.template.preferences.internal.db

import app.cash.sqldelight.async.coroutines.synchronous
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import kmp.template.preferences.db.PreferencesDb
import java.util.Properties as JvmProperties

internal class JvmDatabaseDriverFactory : DatabaseDriverFactory {

    override fun createDriver(): SqlDriver =
        JdbcSqliteDriver(
            url = DB_URL_PROTOCOL + DB_DIRECTORY + DatabaseConstants.DB_NAME,
            properties = JvmProperties(),
            schema = PreferencesDb.Schema.synchronous()
        )

    private companion object {
        const val DB_URL_PROTOCOL = "jdbc:sqlite:"
        val DB_DIRECTORY: String = System.getProperty("java.io.tmpdir").orEmpty()
    }
}
