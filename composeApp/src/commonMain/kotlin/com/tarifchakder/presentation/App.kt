package com.tarifchakder.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.materializekmp.DynamicTheme
import com.tarifchakder.presentation.component.SurfaceCard
import com.tarifchakder.presentation.component.ThemeToggleFab
import com.tarifchakder.presentation.screen.MainContentScreen
import com.tarifchakder.presentation.screen.SidebarScreen
import com.tarifchakder.presentation.widget.ScrollView
import com.tarifchakder.theme.seedColor
import com.tarifchakder.theme.typography

private val CompactPadding = 12.dp
private val MediumPadding = 20.dp
private val ExpandedPadding = 30.dp
private val SidebarWidth = 350.dp

@Composable
fun App() {
    val systemDarkTheme = isSystemInDarkTheme()
    var isDarkTheme by remember { mutableStateOf(systemDarkTheme) }

    DynamicTheme(
        seedColor = seedColor,
        isDarkTheme = isDarkTheme,
        typography = typography()
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .background(androidx.compose.material3.MaterialTheme.colorScheme.surface)
        ) {
            val breakpoint = when {
                maxWidth < 550.dp -> WindowSizeClass.Compact
                maxWidth < 1000.dp -> WindowSizeClass.Medium
                else -> WindowSizeClass.Expanded
            }

            val contentPadding = when (breakpoint) {
                WindowSizeClass.Compact -> CompactPadding
                WindowSizeClass.Medium -> MediumPadding
                WindowSizeClass.Expanded -> ExpandedPadding
            }

            var showMobileSidebarDetails by remember { mutableStateOf(false) }

            if (breakpoint == WindowSizeClass.Expanded) {
                ExpandedLayout(contentPadding = contentPadding)
            } else {
                CompactLayout(
                    breakpoint = breakpoint,
                    contentPadding = contentPadding,
                    showSidebarDetails = showMobileSidebarDetails,
                    onToggleSidebarDetails = { showMobileSidebarDetails = !showMobileSidebarDetails }
                )
            }

            ThemeToggleFab(
                isDarkTheme = isDarkTheme,
                onToggleTheme = { isDarkTheme = !isDarkTheme },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(20.dp)
            )
        }
    }
}

@Composable
private fun ExpandedLayout(contentPadding: Dp) {
    Row(modifier = Modifier.fillMaxSize()) {
        SurfaceCard(
            modifier = Modifier
                .fillMaxHeight()
                .width(SidebarWidth)
                .padding(start = contentPadding, top = contentPadding, bottom = contentPadding)
        ) {
            ScrollView(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(SidebarWidth),
                verticalArrangement = Arrangement.Top
            ) {
                SidebarScreen(
                    modifier = Modifier.fillMaxSize(),
                    showDetails = true,
                    showToggle = false,
                    onToggleDetails = null
                )
            }
        }

        ScrollView(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            MainContentScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
                breakpoint = WindowSizeClass.Expanded
            )
        }
    }
}

@Composable
private fun CompactLayout(
    breakpoint: WindowSizeClass,
    contentPadding: Dp,
    showSidebarDetails: Boolean,
    onToggleSidebarDetails: () -> Unit
) {
    ScrollView(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding),
        verticalArrangement = Arrangement.Top
    ) {
        SurfaceCard(modifier = Modifier.fillMaxWidth()) {
            SidebarScreen(
                modifier = Modifier.fillMaxWidth(),
                showDetails = showSidebarDetails,
                showToggle = true,
                onToggleDetails = onToggleSidebarDetails
            )
        }

        Spacer(modifier = Modifier.height(contentPadding))

        MainContentScreen(
            modifier = Modifier.fillMaxWidth(),
            breakpoint = breakpoint
        )
    }
}
