package kmp.template.feature.sample.presentation.network.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path
import kmp.template.feature.sample.presentation.network.model.Artwork
import kmp.template.feature.sample.presentation.network.model.ArtworksResponse

internal class ArtworksRestClient(
    private val httpClient: HttpClient
) {

    suspend fun getArtworks(): List<Artwork> =
        httpClient.get {
            url {
                path("api", "v1", "artworks")
                parameters.append("limit", API_LIMIT)
                parameters.append("fields", API_FIELDS)
            }
        }.body<ArtworksResponse>().data

    companion object {

        internal const val API_BASE_URL = "https://api.artic.edu/"
        internal const val API_LIMIT = "20"
        internal const val API_FIELDS = "id,title,artist_display,medium_display"
    }
}
