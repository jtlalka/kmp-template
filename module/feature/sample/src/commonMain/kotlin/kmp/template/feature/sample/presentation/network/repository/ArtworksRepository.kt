package kmp.template.feature.sample.presentation.network.repository

import kmp.template.feature.sample.presentation.network.model.Artwork

internal class ArtworksRepository(
    private val artworksRestClient: ArtworksRestClient
) {

    suspend fun getArtworks(): Result<List<Artwork>> = runCatching {
        artworksRestClient.getArtworks()
    }
}
