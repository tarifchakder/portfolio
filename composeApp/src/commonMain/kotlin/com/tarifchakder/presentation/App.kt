package com.tarifchakder.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import com.tarifchakder.data.platformHttpClientEngine
import com.tarifchakder.domain.NavDestination
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.presentation.component.AuroraBackground
import com.tarifchakder.presentation.component.LocalBackdropHaze
import com.tarifchakder.presentation.component.LocalPageHaze
import com.tarifchakder.presentation.component.ThemeToggleButton
import com.tarifchakder.presentation.screen.MainContentScreen
import com.tarifchakder.presentation.screen.ProfilePanel
import com.tarifchakder.presentation.widget.FloatingNavBar
import com.tarifchakder.presentation.widget.NavBarHeight
import com.tarifchakder.presentation.widget.ScrollView
import com.tarifchakder.theme.AppTheme
import com.tarifchakder.theme.LocalGlass
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

private val CompactPadding = 16.dp
private val MediumPadding = 24.dp
private val ExpandedPadding = 28.dp
private val RailWidth = 360.dp
private val RailGap = 24.dp

private val imageHttpClient by lazy {
    io.ktor.client.HttpClient(platformHttpClientEngine())
}

@Composable
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context)
            .components {
                add(KtorNetworkFetcherFactory(httpClient = { imageHttpClient }))
            }
            .build()
    }

    val systemDarkTheme = isSystemInDarkTheme()
    var isDarkTheme by remember { mutableStateOf(systemDarkTheme) }

    SystemBarsAppearance(useDarkIcons = !isDarkTheme)

    AppTheme(isDarkTheme = isDarkTheme) {
        val glass = LocalGlass.current

        // Two independent sources. `backdropHaze` captures only the aurora, so in-page panels can
        // frost it without sampling themselves. `pageHaze` captures aurora + page, so floating
        // chrome smears the content that scrolls beneath it.
        val backdropHaze = remember { HazeState() }
        val pageHaze = remember { HazeState() }

        CompositionLocalProvider(
            LocalBackdropHaze provides backdropHaze,
            LocalPageHaze provides pageHaze
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxSize()
                    .background(glass.base)
            ) {
                val breakpoint = when {
                    maxWidth < 550.dp -> WindowSizeClass.Compact
                    maxWidth < 1000.dp -> WindowSizeClass.Medium
                    else -> WindowSizeClass.Expanded
                }
                val pad = when (breakpoint) {
                    WindowSizeClass.Compact -> CompactPadding
                    WindowSizeClass.Medium -> MediumPadding
                    WindowSizeClass.Expanded -> ExpandedPadding
                }
                val railLayout = breakpoint == WindowSizeClass.Expanded

                var showMobileSidebarDetails by remember { mutableStateOf(false) }
                var currentDestination by remember { mutableStateOf(NavDestination.Home) }

                // -- Page (Haze source for the floating chrome) -----------------------------------
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .hazeSource(state = pageHaze)
                ) {
                    // Backdrop is edge-to-edge and sits behind the system bars on purpose.
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeSource(state = backdropHaze)
                    ) {
                        AuroraBackground(modifier = Modifier.fillMaxSize())
                    }

                    Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
                        if (railLayout) {
                            RailLayout(pad = pad, currentDestination = currentDestination)
                        } else {
                            StackedLayout(
                                breakpoint = breakpoint,
                                pad = pad,
                                showSidebarDetails = showMobileSidebarDetails,
                                onToggleSidebarDetails = { showMobileSidebarDetails = !showMobileSidebarDetails },
                                currentDestination = currentDestination
                            )
                        }
                    }
                }

                // -- Floating chrome (drawn above the source, so it blurs the page) ---------------
                Box(modifier = Modifier.fillMaxSize().windowInsetsPadding(WindowInsets.safeDrawing)) {
                    FloatingNavBar(
                        currentDestination = currentDestination,
                        onNavigate = { currentDestination = it },
                        modifier = if (railLayout) {
                            Modifier.align(Alignment.TopEnd).padding(pad)
                        } else {
                            Modifier
                                .align(Alignment.BottomCenter)
                                .fillMaxWidth()
                                .padding(horizontal = pad, vertical = pad)
                        },
                        fillWidth = !railLayout
                    )

                    ThemeToggleButton(
                        isDarkTheme = isDarkTheme,
                        onToggleTheme = { isDarkTheme = !isDarkTheme },
                        modifier = if (railLayout) {
                            Modifier.align(Alignment.BottomEnd).padding(pad)
                        } else {
                            Modifier.align(Alignment.TopEnd).padding(pad)
                        }
                    )
                }
            }
        }
    }
}

/** Desktop: a persistent profile rail beside an independently scrolling content column. */
@Composable
private fun RailLayout(
    pad: Dp,
    currentDestination: NavDestination
) {
    Row(modifier = Modifier.fillMaxSize().padding(horizontal = pad)) {
        ScrollView(
            modifier = Modifier
                .width(RailWidth)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(Modifier.height(pad))
            ProfilePanel(
                modifier = Modifier.fillMaxWidth(),
                showDetails = true,
                showToggle = false,
                onToggleDetails = null
            )
            Spacer(Modifier.height(pad))
        }

        Spacer(Modifier.width(RailGap))

        val contentScroll = rememberScrollState()
        ResetScrollOnSectionChange(currentDestination, contentScroll)

        ScrollView(
            modifier = Modifier.weight(1f).fillMaxHeight(),
            scrollState = contentScroll,
            verticalArrangement = Arrangement.Top
        ) {
            // Clears the floating nav pill anchored to the top-right.
            Spacer(Modifier.height(NavBarHeight + pad * 2))
            MainContentScreen(
                modifier = Modifier.fillMaxWidth(),
                breakpoint = WindowSizeClass.Expanded,
                currentDestination = currentDestination
            )
            Spacer(Modifier.height(pad * 2))
        }
    }
}

/** Phone / tablet: everything in one column, with content flowing under the bottom nav. */
@Composable
private fun StackedLayout(
    breakpoint: WindowSizeClass,
    pad: Dp,
    showSidebarDetails: Boolean,
    onToggleSidebarDetails: () -> Unit,
    currentDestination: NavDestination
) {
    val scroll = rememberScrollState()
    ResetScrollOnSectionChange(currentDestination, scroll)

    ScrollView(
        modifier = Modifier.fillMaxSize().padding(horizontal = pad),
        scrollState = scroll,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(Modifier.height(pad))

        ProfilePanel(
            modifier = Modifier.fillMaxWidth(),
            showDetails = showSidebarDetails,
            showToggle = true,
            onToggleDetails = onToggleSidebarDetails
        )

        Spacer(Modifier.height(pad))

        MainContentScreen(
            modifier = Modifier.fillMaxWidth(),
            breakpoint = breakpoint,
            currentDestination = currentDestination
        )

        // Runway so the last card can clear the floating bottom nav.
        Spacer(Modifier.height(NavBarHeight + pad * 3))
    }
}

/**
 * Switching sections should land the reader at the top of the new one. Without this the page keeps
 * whatever offset the previous (often much longer) section had, dropping you into its middle.
 */
@Composable
private fun ResetScrollOnSectionChange(destination: NavDestination, scrollState: ScrollState) {
    LaunchedEffect(destination) {
        scrollState.animateScrollTo(0)
    }
}
