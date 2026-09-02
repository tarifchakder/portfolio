package com.tarifchakder.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.theme.LocalGlass
import com.tarifchakder.theme.glassFill
import com.tarifchakder.theme.glassStroke
import com.tarifchakder.util.noRippleClickable

private val ButtonShape = RoundedCornerShape(999.dp)

/** Filled call-to-action carrying the brand gradient. One per view, at most. */
@Composable
fun PrimaryGlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null
) {
    val g = LocalGlass.current
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(if (hovered) 1.04f else 1f, tween(200), label = "primaryScale")
    val elevation by animateDpAsState(if (hovered) 22.dp else 12.dp, tween(200), label = "primaryGlow")

    Row(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .glassShadow(ButtonShape, g, elevation)
            .clip(ButtonShape)
            .background(g.accentGradient)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .noRippleClickable(interactionSource, onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onPrimary,
            maxLines = 1
        )
    }
}

/** Neutral glass action, used alongside a [PrimaryGlassButton]. */
@Composable
fun SecondaryGlassButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    trailing: (@Composable () -> Unit)? = null
) {
    val g = LocalGlass.current
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(if (hovered) 1.04f else 1f, tween(200), label = "secondaryScale")

    Row(
        modifier = modifier
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .clip(ButtonShape)
            .glassFill(ButtonShape, g, raised = hovered)
            .glassStroke(ButtonShape, g)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .noRippleClickable(interactionSource, onClick = onClick)
            .padding(horizontal = 22.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(9.dp)
    ) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = g.textPrimary,
                modifier = Modifier.size(18.dp)
            )
        }
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
            color = g.textPrimary,
            maxLines = 1
        )
        trailing?.invoke()
    }
}
