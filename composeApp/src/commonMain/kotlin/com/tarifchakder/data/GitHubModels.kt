package com.tarifchakder.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubRepo(
    val name: String,
    @SerialName("html_url") val htmlUrl: String,
    val description: String? = null,
    @SerialName("stargazers_count") val stargazersCount: Int = 0,
    val language: String? = null,
    val fork: Boolean = false,
    val owner: GitHubOwner? = null
)

@Serializable
data class GitHubOwner(
    val login: String
)
