package com.tarifchakder.platform

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import platform.Foundation.NSTemporaryDirectory
import platform.Foundation.NSURL
import platform.Foundation.writeToFile
import platform.Foundation.NSData
import platform.Foundation.create
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.UIKit.UIApplication

actual class PdfDownloader {
    @OptIn(ExperimentalForeignApi::class)
    actual fun openOrDownloadPdf(fileName: String, bytes: ByteArray) {
        try {
            val path = "${NSTemporaryDirectory()}$fileName"
            val nsData = bytes.usePinned { pinned ->
                NSData.create(bytes = pinned.addressOf(0), length = bytes.size.toULong())
            }
            nsData.writeToFile(path, true)
            val url = NSURL.fileURLWithPath(path)
            UIApplication.sharedApplication.openURL(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

@Composable
actual fun rememberPdfDownloader(): PdfDownloader = remember { PdfDownloader() }
