package com.tarifchakder.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import kotlinx.browser.window

actual class PdfDownloader {
    actual fun openOrDownloadPdf(fileName: String, bytes: ByteArray) {
        window.open(fileName, "_blank")
    }
}

@Composable
actual fun rememberPdfDownloader(): PdfDownloader = remember { PdfDownloader() }
