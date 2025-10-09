package com.tarifchakder

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform