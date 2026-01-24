package kmp.template.feature.sample.presentation.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class Artwork(
    @SerialName("id") val id: Long = 0L,
    @SerialName("title") val title: String = "",
    @SerialName("artist_display") val artist: String = "",
    @SerialName("medium_display") val display: String = ""
)
