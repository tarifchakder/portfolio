package com.tarifchakder.presentation.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.tarifchakder.data.GitHubApi
import com.tarifchakder.data.GitHubRepo
import com.tarifchakder.presentation.component.AutoFitGrid
import com.tarifchakder.presentation.component.GlassCard
import com.tarifchakder.presentation.component.SectionPanel
import com.tarifchakder.theme.LocalGlass
import com.tarifchakder.theme.glassFill
import com.tarifchakder.theme.glassStroke
import com.tarifchakder.util.noRippleClickable
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.googleplay
import portfolio.composeapp.generated.resources.noto_sans_bengali
import portfolio.composeapp.generated.resources.work_github_error
import portfolio.composeapp.generated.resources.work_no_repos
import portfolio.composeapp.generated.resources.work_repo_fallback_subtitle
import portfolio.composeapp.generated.resources.work_subtitle
import portfolio.composeapp.generated.resources.work_tab_github
import portfolio.composeapp.generated.resources.work_tab_google_play
import portfolio.composeapp.generated.resources.work_title

private const val GITHUB_USERNAME = "tarifchakder"

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
    data class Vector(val icon: androidx.compose.ui.graphics.vector.ImageVector) : TabIcon
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
            painter = painterResource(Res.drawable.googleplay),
            contentDescription = null,
            modifier = Modifier.size(size).clip(RoundedCornerShape(4.dp))
        )
    }
}

// Declaration order drives both the tab bar and the sliding indicator, so GitHub leading here
// is what puts it first on screen.
private enum class WorkTab(val labelRes: StringResource, val icon: TabIcon) {
    GitHub(Res.string.work_tab_github, TabIcon.Vector(Icons.Rounded.Code)),
    GooglePlay(Res.string.work_tab_google_play, TabIcon.GooglePlayLogo)
}

@Composable
fun WorkScreen() {
    var selectedTab by remember { mutableStateOf(WorkTab.GitHub) }

    SectionPanel(
        title = stringResource(Res.string.work_title),
        icon = Icons.Rounded.GridView,
        subtitle = stringResource(Res.string.work_subtitle),
        modifier = Modifier.fillMaxWidth()
    ) {
        WorkTabBar(selectedTab = selectedTab, onSelect = { selectedTab = it })

        Box(modifier = Modifier.padding(top = 20.dp)) {
            when (selectedTab) {
                WorkTab.GitHub -> GitHubProjectsTab()
                WorkTab.GooglePlay -> GooglePlayTab()
            }
        }
    }
}

private val TabItemWidth = 132.dp
private val TabBarHeight = 46.dp

/** Segmented glass control with a sliding accent indicator, mirroring the main nav's behaviour. */
@Composable
private fun WorkTabBar(selectedTab: WorkTab, onSelect: (WorkTab) -> Unit) {
    val g = LocalGlass.current
    val shape = RoundedCornerShape(999.dp)
    val tabs = WorkTab.entries

    Box(
        modifier = Modifier
            .height(TabBarHeight)
            .width(TabItemWidth * tabs.size)
            .clip(shape)
            .glassFill(shape, g)
            .glassStroke(shape, g)
            .padding(4.dp)
    ) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val itemWidth = maxWidth / tabs.size
            val selectedIndex = tabs.indexOf(selectedTab).coerceAtLeast(0)

            val indicatorOffset by animateDpAsState(
                targetValue = itemWidth * selectedIndex,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                ),
                label = "workTabIndicator"
            )

            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .width(itemWidth)
                    .fillMaxHeight()
                    .clip(shape)
                    .background(g.accentGradient)
            )

            Row(modifier = Modifier.fillMaxSize()) {
                tabs.forEach { tab ->
                    WorkTabItem(
                        tab = tab,
                        selected = tab == selectedTab,
                        onClick = { onSelect(tab) },
                        modifier = Modifier.width(itemWidth).fillMaxHeight()
                    )
                }
            }
        }
    }
}

@Composable
private fun WorkTabItem(
    tab: WorkTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val g = LocalGlass.current
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    val contentColor by animateColorAsState(
        targetValue = when {
            selected -> MaterialTheme.colorScheme.onPrimary
            hovered -> g.textPrimary
            else -> g.textTertiary
        },
        animationSpec = tween(220),
        label = "workTabColor"
    )

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .noRippleClickable(interactionSource, onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TabIconView(icon = tab.icon, tint = contentColor, size = 17.dp)
        Text(
            text = stringResource(tab.labelRes),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold
            ),
            color = contentColor,
            maxLines = 1
        )
    }
}

@Composable
private fun GitHubProjectsTab() {
    val g = LocalGlass.current
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
        error -> StatusText(stringResource(Res.string.work_github_error))

        repos == null -> Box(
            modifier = Modifier.fillMaxWidth().padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = g.accent, strokeWidth = 3.dp)
        }

        repos!!.isEmpty() -> StatusText(stringResource(Res.string.work_no_repos))

        // No thumbnailUrl here: GitHub's opengraph image is a full mini repo-card in its own
        // right (name, description, contributor/issue/star/fork counts, language bar) - it isn't
        // a logo. Layering our own title/subtitle over it just repeated the same text twice.
        // The icon-tile placeholder ProjectCard already falls back to is what Google Play uses
        // too, so both tabs read as one consistent grid.
        else -> AutoFitGrid(items = repos!!) { repo ->
            ProjectCard(
                title = repo.name,
                subtitle = repo.description ?: repo.language ?: stringResource(Res.string.work_repo_fallback_subtitle),
                thumbnailUrl = null,
                starCount = if (repo.stargazersCount > 0) repo.stargazersCount else null,
                icon = TabIcon.Vector(Icons.Rounded.Code),
                url = repo.htmlUrl
            )
        }
    }
}

@Composable
private fun GooglePlayTab() {
    AutoFitGrid(items = GOOGLE_PLAY_APPS) { app ->
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

@Composable
private fun StatusText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyMedium,
        color = LocalGlass.current.textTertiary
    )
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
    val g = LocalGlass.current
    val uriHandler = LocalUriHandler.current

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        onClick = { uriHandler.openUri(url) }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(thumbnailAspectRatio)
                    .background(g.accentSoft)
            ) {
                var thumbnailFailed by remember(thumbnailUrl) { mutableStateOf(false) }

                // The icon sits underneath as a permanent placeholder, so the tile is never an
                // empty rectangle while the image loads - or if it never arrives at all.
                Box(modifier = Modifier.align(Alignment.Center)) {
                    TabIconView(icon = icon, tint = g.accent, size = 40.dp)
                }

                if (thumbnailUrl != null && !thumbnailFailed) {
                    AsyncImage(
                        model = thumbnailUrl,
                        contentDescription = title,
                        contentScale = ContentScale.Crop,
                        onError = { thumbnailFailed = true },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                if (starCount != null) {
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopEnd)
                            .clip(RoundedCornerShape(999.dp))
                            .glassFill(RoundedCornerShape(999.dp), g, raised = true)
                            .glassStroke(RoundedCornerShape(999.dp), g)
                            .padding(horizontal = 9.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = g.accent,
                            modifier = Modifier.size(12.dp)
                        )
                        Text(
                            text = starCount.toString(),
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                            color = g.textPrimary
                        )
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = fallbackTextFontFamily()
                        ),
                        color = g.textPrimary,
                        maxLines = 1,
                        modifier = Modifier.weight(1f, fill = false)
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.OpenInNew,
                        contentDescription = null,
                        tint = g.textTertiary,
                        modifier = Modifier.size(15.dp)
                    )
                }
                if (subtitle != null) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = g.textTertiary,
                        minLines = 2,
                        maxLines = 2
                    )
                }
            }
        }
    }
}
