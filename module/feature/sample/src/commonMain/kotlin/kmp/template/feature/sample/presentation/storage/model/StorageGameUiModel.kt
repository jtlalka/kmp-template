package kmp.template.feature.sample.presentation.storage.model

import kotlinx.serialization.Serializable

@Serializable
internal data class StorageGameUiModel(
    val firstPlayerRound: Boolean = true,
    val firstPlayerSign: String = "X",
    val secondPlayerSign: String = "O",
    val gameBoard: List<String> = List(BOARD_SIZE) { EMPTY_PLACE }
) {

    companion object {
        const val EMPTY_PLACE = "."
        const val BOARD_SIZE = 9
    }
}
