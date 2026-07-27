package com.tarifchakder

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.tarifchakder.presentation.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "portfolio",
    ) {
        App()
    }
}