package kmp.template.feature.sample.presentation.network.model

import kotlinx.serialization.Serializable

@Serializable
internal data class ArtworksResponse(
    val data: List<Artwork>
)
