package kmp.template.network.client

import io.ktor.client.plugins.logging.Logger
import co.touchlab.kermit.Logger.Companion as KermitLogger

internal class HttpClientLogger : Logger {

    override fun log(message: String) {
        KermitLogger.d(tag = TAG, messageString = message)
    }

    private companion object Companion {
        const val TAG = "Network"
    }
}
