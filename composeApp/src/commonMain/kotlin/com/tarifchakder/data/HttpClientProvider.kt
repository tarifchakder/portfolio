package com.tarifchakder.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine

expect fun platformHttpClientEngine(): HttpClientEngine
