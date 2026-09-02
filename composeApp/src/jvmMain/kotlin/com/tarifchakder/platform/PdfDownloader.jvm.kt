package com.tarifchakder.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import java.awt.Desktop
import java.io.File

actual class PdfDownloader {
    actual fun openOrDownloadPdf(fileName: String, bytes: ByteArray) {
        try {
            val downloadsDir = File(System.getProperty("user.home"), "Downloads")
            downloadsDir.mkdirs()
            val file = File(downloadsDir, fileName)
            file.writeBytes(bytes)
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.OPEN)) {
                Desktop.getDesktop().open(file)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
actual fun rememberPdfDownloader(): PdfDownloader = remember { PdfDownloader() }
