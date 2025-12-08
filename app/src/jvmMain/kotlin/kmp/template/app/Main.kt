package kmp.template.app

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import kmp.template.app.di.initKoin

fun main() = application {
    initKoin()

    val state = rememberWindowState(
        size = DpSize(800.dp, 600.dp),
        position = WindowPosition(300.dp, 300.dp)
    )

    Window(
        state = state,
        onCloseRequest = ::exitApplication
    ) {
        App()
    }
}
