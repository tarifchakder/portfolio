package com.tarifchakder.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Uniform, theme-independent shadow (equal ambient/spot color) so every card
 * renders the same soft edge on all sides instead of Card's default directional shadow.
 */
fun Modifier.softShadow(shape: Shape, elevation: Dp = 8.dp, alpha: Float = 0.28f): Modifier = this.shadow(
    elevation = elevation,
    shape = shape,
    ambientColor = Color.Black.copy(alpha = alpha),
    spotColor = Color.Black.copy(alpha = alpha)
)

@Composable
fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    return this.clickable(
        interactionSource = interactionSource,
        indication = null,
        onClick = onClick
    )
}

fun Modifier.noRippleClickable(
    interactionSource: MutableInteractionSource,
    onClick: () -> Unit
): Modifier = this.clickable(
    interactionSource = interactionSource,
    indication = null,
    onClick = onClick
)

fun String.urlEncode(): String {
    return this.replace(" ", "%20")
        .replace(",", "%2C")
        .replace(":", "%3A")
        .replace("/", "%2F")
}
