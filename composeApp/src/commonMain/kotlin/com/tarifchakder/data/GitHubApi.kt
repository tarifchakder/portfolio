package com.tarifchakder.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object GitHubApi {
    private val client = HttpClient(platformHttpClientEngine()) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun fetchRepos(username: String): List<GitHubRepo> {
        return client.get("https://api.github.com/users/$username/repos") {
            parameter("sort", "updated")
            parameter("per_page", "30")
        }.body()
    }

    fun thumbnailUrl(owner: String, repo: String): String =
        "https://opengraph.githubassets.com/1/$owner/$repo"
}
