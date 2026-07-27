package com.tarifchakder.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.NavDestination
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.presentation.component.SurfaceCard
import com.tarifchakder.presentation.navigation.PortfolioNavHost
import com.tarifchakder.presentation.widget.AdaptiveNavigationBar

@Composable
fun MainContentScreen(
    modifier: Modifier = Modifier,
    breakpoint: WindowSizeClass,
    currentDestination: NavDestination,
    onNavigate: (NavDestination) -> Unit
) {
    SurfaceCard(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp))
            ) {
                AdaptiveNavigationBar(
                    modifier = if (breakpoint == WindowSizeClass.Expanded) {
                        Modifier.width(500.dp).align(Alignment.TopEnd)
                    } else {
                        Modifier.fillMaxWidth()
                    },
                    currentDestination = currentDestination,
                    onNavigate = onNavigate
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
            ) {
                AnimatedContent(
                    targetState = currentDestination,
                    modifier = Modifier.fillMaxWidth(),
                    transitionSpec = {
                        fadeIn().togetherWith(fadeOut()).using(SizeTransform(clip = false))
                    },
                    label = "BottomNavPageSwitch"
                ) { destination ->
                    PortfolioNavHost(destination = destination, breakpoint = breakpoint)
                }
            }
        }
    }
}
