package kmp.template.app

import androidx.compose.ui.window.ComposeUIViewController
import kmp.template.app.di.initKoin
import kotlin.experimental.ExperimentalNativeApi

@Suppress("FunctionName", "unused")
@OptIn(ExperimentalNativeApi::class)
fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    },
    content = {
        App()
    }
)
