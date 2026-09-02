package com.tarifchakder.platform

import androidx.compose.runtime.Composable

expect class PdfDownloader {
    fun openOrDownloadPdf(fileName: String, bytes: ByteArray)
}

@Composable
expect fun rememberPdfDownloader(): PdfDownloader
