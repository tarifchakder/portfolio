package com.tarifchakder.presentation.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import com.tarifchakder.theme.LocalGlass
import com.tarifchakder.util.noRippleClickable

private val ToggleSize = 52.dp

/**
 * Floating glass theme switch. Uses the chrome material (not a card) because it hovers above the
 * page and should frost whatever scrolls under it.
 */
@Composable
fun ThemeToggleButton(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    modifier: Modifier = Modifier
) {
    val g = LocalGlass.current

    GlassChrome(
        modifier = modifier
            .size(ToggleSize)
            .pointerHoverIcon(PointerIcon.Hand)
            .noRippleClickable(onClick = onToggleTheme),
        shape = CircleShape,
        elevation = 18.dp
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AnimatedContent(
                targetState = isDarkTheme,
                transitionSpec = {
                    (scaleIn(tween(260)) + fadeIn(tween(260)))
                        .togetherWith(scaleOut(tween(200)) + fadeOut(tween(200)))
                },
                label = "themeIcon"
            ) { dark ->
                Icon(
                    imageVector = if (dark) Icons.Rounded.LightMode else Icons.Rounded.DarkMode,
                    contentDescription = if (dark) "Switch to light theme" else "Switch to dark theme",
                    tint = g.accent,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}
