package com.tarifchakder.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tarifchakder.domain.NavDestination
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.presentation.navigation.PortfolioNavHost

/**
 * Hosts the active section. Sections bring their own glass panels, so this layer only owns the
 * transition between them - a short rise-and-fade that keeps the page feeling like one surface.
 */
@Composable
fun MainContentScreen(
    modifier: Modifier = Modifier,
    breakpoint: WindowSizeClass,
    currentDestination: NavDestination
) {
    AnimatedContent(
        targetState = currentDestination,
        modifier = modifier.fillMaxWidth(),
        transitionSpec = {
            (fadeIn(tween(300)) + slideInVertically(tween(360)) { it / 14 })
                .togetherWith(fadeOut(tween(180)))
                .using(SizeTransform(clip = false))
        },
        label = "sectionSwitch"
    ) { destination ->
        PortfolioNavHost(destination = destination, breakpoint = breakpoint)
    }
}
