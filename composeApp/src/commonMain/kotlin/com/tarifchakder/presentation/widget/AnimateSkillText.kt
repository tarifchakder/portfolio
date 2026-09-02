package com.tarifchakder.presentation.widget

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.theme.LocalGlass
import com.tarifchakder.theme.glassFill
import com.tarifchakder.theme.glassStroke
import kotlinx.coroutines.delay

private val SPECIALISMS = listOf(
    "Android",
    "KMP Enthusiast",
    "Blogger",
    "Contributor"
)

/**
 * Rotating specialism badge. The label cross-fades vertically so the pill reads as a single
 * ticker rather than four separate labels.
 */
@Composable
fun AnimateSkillText(modifier: Modifier = Modifier) {
    val g = LocalGlass.current
    val shape = RoundedCornerShape(999.dp)

    var index by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        while (true) {
            delay(3000)
            index = (index + 1) % SPECIALISMS.size
        }
    }

    val pulse = rememberInfiniteTransition(label = "statusPulse")
    val dotAlpha by pulse.animateFloat(
        initialValue = 0.35f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(tween(1400), RepeatMode.Reverse),
        label = "dotAlpha"
    )

    Row(
        modifier = modifier
            .clip(shape)
            .glassFill(shape, g)
            .glassStroke(shape, g)
            .padding(horizontal = 14.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(7.dp)
                .alpha(dotAlpha)
                .clip(CircleShape)
                .background(g.accent)
        )

        Spacer(Modifier.width(9.dp))

        AnimatedContent(
            targetState = SPECIALISMS[index],
            transitionSpec = {
                (slideInVertically(tween(320)) { it } + fadeIn(tween(320)))
                    .togetherWith(slideOutVertically(tween(320)) { -it } + fadeOut(tween(220)))
            },
            label = "specialism"
        ) { label ->
            Text(
                text = label,
                color = g.textSecondary,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}
