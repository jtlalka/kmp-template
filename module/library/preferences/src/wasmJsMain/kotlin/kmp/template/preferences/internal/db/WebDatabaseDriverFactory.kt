package kmp.template.preferences.internal.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.worker.WebWorkerDriver
import org.w3c.dom.Worker

/**
 * JS bridge is not provided yet, please read documentation to enable it for JS target:
 * https://sqldelight.github.io/sqldelight/latest/js_sqlite/
 */
@OptIn(ExperimentalWasmJsInterop::class)
private fun createWorker(): Worker = js(
    """new URL("sqljs.worker.js", import.meta.url)"""
)

internal class WebDatabaseDriverFactory : DatabaseDriverFactory {

    override fun createDriver(): SqlDriver = WebWorkerDriver(
        worker = createWorker()
    )
}
