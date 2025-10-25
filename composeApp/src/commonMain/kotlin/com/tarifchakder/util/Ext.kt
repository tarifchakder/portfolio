package com.tarifchakder.util

fun String.urlEncode(): String {
    return this.replace(" ", "%20")
        .replace(",", "%2C")
        .replace(":", "%3A")
        .replace("/", "%2F")
}