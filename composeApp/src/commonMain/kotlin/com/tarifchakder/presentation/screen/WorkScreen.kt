package com.tarifchakder.presentation.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tarifchakder.data.GitHubApi
import com.tarifchakder.data.GitHubRepo
import com.tarifchakder.util.noRippleClickable
import com.tarifchakder.util.softShadow
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.google_play
import portfolio.composeapp.generated.resources.noto_sans_bengali

private const val GITHUB_USERNAME = "tarifchakder"
private const val GOOGLE_PLAY_URL = "https://play.google.com/store/apps/dev?id=6362563028488118131"

/**
 * Bundled Noto Sans Bengali as an explicit fallback: Bengali titles must not rely on the
 * system/browser having a matching font installed (the platform default font isn't guaranteed to).
 */
@Composable
private fun fallbackTextFontFamily(): FontFamily = FontFamily(Font(Res.font.noto_sans_bengali))

private data class GooglePlayApp(
    val name: String,
    val packageId: String,
    val iconUrl: String
) {
    val storeUrl get() = "https://play.google.com/store/apps/details?id=$packageId"
}

private val GOOGLE_PLAY_APPS = listOf(
    GooglePlayApp(
        name = "কাজী নজরুল ইসলাম রচনাবলী",
        packageId = "mrtech.kazinajrulislam",
        iconUrl = "https://play-lh.googleusercontent.com/UjRsqd-0UyGRvIED8dSZT0at1knZzsU7DoeKnalcIUfzarMftLwoKDOh1QQbkqG78p1AMnodgZYK8SDNeUy3Nw=s256"
    ),
    GooglePlayApp(
        name = "রবীন্দ্রনাথ রচনাসমগ্র",
        packageId = "com.mrtech.rabindranathtagore",
        iconUrl = "https://play-lh.googleusercontent.com/OTxG5BqAv7i7yHVs9AeSQqTT5BGCoQ5Q4YauEBbL5gyglUtHu-pqeJrhKxRyeIVP2NGPQJsRliGjd0EJGuua=s256"
    ),
    GooglePlayApp(
        name = "কবি ও কবিতা",
        packageId = "mrtech.bengali_kabita",
        iconUrl = "https://play-lh.googleusercontent.com/sMgW9cDotAeOff7hNuO0GkiVUNA6QO9YnpVwUDUAPsdFu_HGMn0f9WiJB8MVJABqnueHUNIUKvVDaSGhKA6Uhw=s256"
    )
)

private sealed interface TabIcon {
    data class Vector(val icon: ImageVector) : TabIcon
    object GooglePlayLogo : TabIcon
}

@Composable
private fun TabIconView(icon: TabIcon, tint: Color, size: Dp) {
    when (icon) {
        is TabIcon.Vector -> Icon(
            imageVector = icon.icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(size)
        )

        TabIcon.GooglePlayLogo -> Image(
            painter = painterResource(Res.drawable.google_play),
            contentDescription = null,
            modifier = Modifier.size(size).clip(RoundedCornerShape(4.dp))
        )
    }
}

private enum class WorkTab(val label: String, val icon: TabIcon) {
    GooglePlay("Google Play", TabIcon.GooglePlayLogo),
    GitHub("GitHub Projects", TabIcon.Vector(Icons.Rounded.Code))
}

@Composable
fun WorkScreen() {
    var selectedTab by remember { mutableStateOf(WorkTab.GooglePlay) }

    Column(modifier = Modifier.fillMaxWidth()) {
        WorkTabBar(selectedTab = selectedTab, onSelect = { selectedTab = it })

        Box(modifier = Modifier.padding(top = 20.dp)) {
            when (selectedTab) {
                WorkTab.GitHub -> GitHubProjectsTab()
                WorkTab.GooglePlay -> GooglePlayTab()
            }
        }
    }
}

@Composable
private fun WorkTabBar(selectedTab: WorkTab, onSelect: (WorkTab) -> Unit) {
    Row(
        modifier = Modifier
            .softShadow(RoundedCornerShape(999.dp), elevation = 6.dp, alpha = 0.16f)
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        WorkTab.entries.forEach { tab ->
            WorkTabItem(
                tab = tab,
                selected = selectedTab == tab,
                onClick = { onSelect(tab) }
            )
        }
    }
}

@Composable
private fun WorkTabItem(tab: WorkTab, selected: Boolean, onClick: () -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    val backgroundAlpha by animateFloatAsState(
        targetValue = when {
            selected -> 1f
            hovered -> 0.12f
            else -> 0f
        },
        animationSpec = tween(220)
    )
    val backgroundColor = MaterialTheme.colorScheme.primary.copy(alpha = backgroundAlpha)

    val contentColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.onPrimary
        } else {
            MaterialTheme.colorScheme.onSurface
        },
        animationSpec = tween(220)
    )
    val contentAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0.7f,
        animationSpec = tween(220)
    )

    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(backgroundColor)
            .hoverable(interactionSource)
            .noRippleClickable(interactionSource, onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TabIconView(
            icon = tab.icon,
            tint = contentColor.copy(alpha = contentAlpha),
            size = 18.dp
        )
        Text(
            text = tab.label,
            style = MaterialTheme.typography.labelLarge,
            color = contentColor.copy(alpha = contentAlpha),
            fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium
        )
    }
}

@Composable
private fun GitHubProjectsTab() {
    var repos by remember { mutableStateOf<List<GitHubRepo>?>(null) }
    var error by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        try {
            repos = GitHubApi.fetchRepos(GITHUB_USERNAME)
                .filter { !it.fork }
                .sortedByDescending { it.stargazersCount }
        } catch (t: Throwable) {
            error = true
        }
    }

    when {
        error -> Text(
            text = "Couldn't load GitHub projects right now.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        repos == null -> Box(modifier = Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }

        repos!!.isEmpty() -> Text(
            text = "No public repositories found.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )

        else -> ProjectGrid(items = repos!!) { repo ->
            ProjectCard(
                title = repo.name,
                subtitle = repo.description ?: repo.language ?: "GitHub repository",
                thumbnailUrl = GitHubApi.thumbnailUrl(repo.owner?.login ?: GITHUB_USERNAME, repo.name),
                starCount = if (repo.stargazersCount > 0) repo.stargazersCount else null,
                icon = TabIcon.Vector(Icons.Rounded.Code),
                url = repo.htmlUrl
            )
        }
    }
}

@Composable
private fun GooglePlayTab() {
    ProjectGrid(items = GOOGLE_PLAY_APPS) { app ->
        ProjectCard(
            title = app.name,
            thumbnailUrl = app.iconUrl,
            starCount = null,
            icon = TabIcon.GooglePlayLogo,
            url = app.storeUrl,
            thumbnailAspectRatio = 1f
        )
    }
}

/**
 * Auto-fit grid (like CSS grid-template-columns: repeat(auto-fit, minmax(minCellWidth, 1fr))):
 * column count is derived from the available width, and each card stretches evenly to fill it.
 */
@Composable
private fun <T> ProjectGrid(
    items: List<T>,
    minCellWidth: androidx.compose.ui.unit.Dp = 240.dp,
    spacing: androidx.compose.ui.unit.Dp = 16.dp,
    content: @Composable (T) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val columns = ((maxWidth + spacing) / (minCellWidth + spacing))
            .toInt()
            .coerceAtLeast(1)

        Column(verticalArrangement = Arrangement.spacedBy(spacing)) {
            items.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)) {
                            content(item)
                        }
                    }
                    repeat(columns - rowItems.size) {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}

@Composable
private fun ProjectCard(
    title: String,
    subtitle: String? = null,
    thumbnailUrl: String?,
    starCount: Int?,
    icon: TabIcon,
    url: String,
    thumbnailAspectRatio: Float = 16f / 9f
) {
    val uriHandler = LocalUriHandler.current
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(
        targetValue = if (hovered) 1.03f else 1f,
        animationSpec = tween(200)
    )
    val shadowElevation by animateDpAsState(
        targetValue = if (hovered) 14.dp else 6.dp,
        animationSpec = tween(200)
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .softShadow(MaterialTheme.shapes.large, elevation = shadowElevation)
            .hoverable(interactionSource)
            .noRippleClickable(interactionSource, onClick = { uriHandler.openUri(url) }),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(thumbnailAspectRatio)
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
        ) {
            var thumbnailFailed by remember(thumbnailUrl) { mutableStateOf(false) }

            if (thumbnailUrl != null && !thumbnailFailed) {
                AsyncImage(
                    model = thumbnailUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    onError = { thumbnailFailed = true },
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(modifier = Modifier.align(Alignment.Center)) {
                    TabIconView(icon = icon, tint = MaterialTheme.colorScheme.primary, size = 40.dp)
                }
            }

            if (starCount != null) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                        .clip(RoundedCornerShape(999.dp))
                        .background(MaterialTheme.colorScheme.surfaceColorAtElevation(4.dp).copy(alpha = 0.9f))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(12.dp)
                    )
                    Text(
                        text = starCount.toString(),
                        style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = fallbackTextFontFamily()
                    ),
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    modifier = Modifier.weight(1f, fill = false)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.OpenInNew,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
                    modifier = Modifier.size(14.dp)
                )
            }
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f),
                    minLines = 2,
                    maxLines = 2
                )
            }
        }
    }
}
