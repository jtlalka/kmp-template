package kmp.template.network.client

import io.ktor.client.HttpClient
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.atomicfu.locks.synchronized

internal object HttpClientCache {

    private val clients = HashMap<String, HttpClient>()
    private val lock = SynchronizedObject()

    fun getOrCreate(baseUrl: String): HttpClient =
        synchronized(lock) {
            clients.getOrPut(baseUrl) {
                HttpClientFactory.create(baseUrl)
            }
        }

    fun closeAllClients() {
        synchronized(lock) {
            clients.values.forEach { it.close() }
            clients.clear()
        }
    }
}
