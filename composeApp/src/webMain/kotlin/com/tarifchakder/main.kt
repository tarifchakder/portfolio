package com.tarifchakder

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.tarifchakder.presentation.App
import com.tarifchakder.presentation.screen.ZPlayerTermsApp
import kotlinx.browser.window

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val isZPlayerTermsPage = window.location.pathname
        .trimEnd('/')
        .endsWith("/zplayer/terms")

    ComposeViewport {
        if (isZPlayerTermsPage) {
            ZPlayerTermsApp()
        } else {
            App()
        }
    }
}
