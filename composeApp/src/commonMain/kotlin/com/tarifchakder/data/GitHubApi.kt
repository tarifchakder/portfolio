package com.tarifchakder.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object GitHubApi {
    private val client by lazy {
        HttpClient(platformHttpClientEngine()) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    coerceInputValues = true
                })
            }
        }
    }

    suspend fun fetchRepos(username: String): List<GitHubRepo> {
        return client.get("https://api.github.com/users/$username/repos") {
            header(HttpHeaders.UserAgent, "tarifchakder-portfolio")
            header(HttpHeaders.Accept, "application/vnd.github.v3+json")
            parameter("sort", "updated")
            parameter("per_page", "30")
        }.body()
    }
}
