package com.tarifchakder.presentation.component

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.tarifchakder.theme.LocalGlass
import kotlin.math.max

/**
 * The colour field that sits behind every pane of glass.
 *
 * Glass is only legible when there is something varied behind it, so this draws a slow-drifting
 * aurora of four wide radial blobs over a vertical base gradient. The motion is deliberately long
 * (25-40s per cycle) - it should register as ambient depth, never as an animation competing with
 * the content.
 */
@Composable
fun AuroraBackground(modifier: Modifier = Modifier) {
    val g = LocalGlass.current
    val transition = rememberInfiniteTransition(label = "aurora")

    @Composable
    fun drift(durationMillis: Int, label: String): Float {
        val value by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = label
        )
        return value
    }

    val d1 = drift(29_000, "d1")
    val d2 = drift(37_000, "d2")
    val d3 = drift(24_000, "d3")
    val d4 = drift(33_000, "d4")

    val blobAlpha = if (g.isDark) 0.55f else 0.5f

    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height
        val span = max(w, h)

        drawRect(
            brush = Brush.verticalGradient(
                listOf(g.base, g.baseDeep, g.base),
                startY = 0f,
                endY = h
            )
        )

        auroraBlob(g.aurora[0], Offset(w * (0.10f + 0.22f * d1), h * (0.06f + 0.16f * d2)), span * 0.62f, blobAlpha)
        auroraBlob(g.aurora[1], Offset(w * (0.88f - 0.26f * d2), h * (0.20f + 0.18f * d3)), span * 0.55f, blobAlpha * 0.9f)
        auroraBlob(g.aurora[2], Offset(w * (0.22f + 0.30f * d3), h * (0.86f - 0.20f * d1)), span * 0.58f, blobAlpha * 0.8f)
        auroraBlob(g.aurora[3], Offset(w * (0.78f - 0.20f * d4), h * (0.72f + 0.16f * d4)), span * 0.44f, blobAlpha * 0.6f)

        // Vignette: pulls focus inward and keeps the screen edges calm behind floating chrome.
        drawRect(
            brush = Brush.radialGradient(
                colors = listOf(Color.Transparent, g.baseDeep.copy(alpha = if (g.isDark) 0.75f else 0.35f)),
                center = Offset(w / 2f, h / 2f),
                radius = span * 0.78f
            )
        )
    }
}

private fun DrawScope.auroraBlob(color: Color, center: Offset, radius: Float, alpha: Float) {
    drawCircle(
        brush = Brush.radialGradient(
            colors = listOf(
                color.copy(alpha = alpha),
                color.copy(alpha = alpha * 0.42f),
                Color.Transparent
            ),
            center = center,
            radius = radius
        ),
        radius = radius,
        center = center
    )
}
