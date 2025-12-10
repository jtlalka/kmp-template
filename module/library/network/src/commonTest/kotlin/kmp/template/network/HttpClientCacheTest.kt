package kmp.template.network

import kmp.template.network.client.HttpClientCache
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertNotSame
import kotlin.test.assertSame

class HttpClientCacheTest {

    private val tested = HttpClientCache

    @AfterTest
    fun tearDown() {
        tested.closeAllClients()
    }

    @Test
    fun `provides the same instance of HttpClient for the same host name`() {
        val host = "api.example.com"

        val client1 = tested.getOrCreate(host)
        val client2 = tested.getOrCreate(host)

        assertSame(expected = client1, actual = client2)
    }

    @Test
    fun `provides different instances of HttpClient for the different host name`() {
        val hostA = "api.example.com"
        val hostB = "auth.example.com"

        val clientA = tested.getOrCreate(hostA)
        val clientB = tested.getOrCreate(hostB)

        assertNotSame(illegal = clientA, actual = clientB)
    }
}
