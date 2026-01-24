package kmp.template.network.client

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.Url
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal object HttpClientFactory {

    private const val DEFAULT_TIMEOUT = 30_000L

    fun create(baseUrl: String) = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    useAlternativeNames = false
                    coerceInputValues = true
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = DEFAULT_TIMEOUT
            connectTimeoutMillis = DEFAULT_TIMEOUT
            socketTimeoutMillis = DEFAULT_TIMEOUT
        }
        install(Logging) {
            level = LogLevel.INFO
            logger = HttpClientLogger()
        }
        defaultRequest {
            val base = Url(baseUrl)
            url {
                protocol = base.protocol
                host = base.host
                port = base.port
            }
        }
    }
}
