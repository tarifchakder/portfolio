package com.tarifchakder.presentation.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.NavDestination
import com.tarifchakder.presentation.component.GlassChrome
import com.tarifchakder.theme.LocalGlass
import com.tarifchakder.util.noRippleClickable
import org.jetbrains.compose.resources.stringResource

val NavBarHeight = 62.dp
private val NavPillItemWidth = 112.dp
private val NavInnerPadding = 6.dp

private val NavDestination.icon: ImageVector
    get() = when (this) {
        NavDestination.Home -> Icons.Rounded.Person
        NavDestination.Resume -> Icons.Rounded.Description
        NavDestination.WORK -> Icons.Rounded.GridView
    }

/**
 * Floating glass navigation. Anchored top-right as a compact pill on desktop, and bottom-centre
 * full-width on smaller screens - in both cases it sits above the page so content frosts as it
 * scrolls beneath.
 *
 * The selection indicator is a single pill that slides between slots rather than one background
 * per item, which is what makes switching read as one continuous movement.
 */
@Composable
fun FloatingNavBar(
    currentDestination: NavDestination,
    onNavigate: (NavDestination) -> Unit,
    modifier: Modifier = Modifier,
    fillWidth: Boolean = false
) {
    val g = LocalGlass.current
    val destinations = NavDestination.entries

    GlassChrome(
        modifier = modifier
            .height(NavBarHeight)
            .then(if (fillWidth) Modifier else Modifier.width(NavPillItemWidth * destinations.size)),
        shape = CircleShape
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().padding(NavInnerPadding)
        ) {
            val itemWidth = maxWidth / destinations.size
            val selectedIndex = destinations.indexOf(currentDestination).coerceAtLeast(0)

            val indicatorOffset by animateDpAsState(
                targetValue = itemWidth * selectedIndex,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMediumLow
                ),
                label = "navIndicator"
            )

            Box(
                modifier = Modifier
                    .offset(x = indicatorOffset)
                    .width(itemWidth)
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(g.accentGradient)
            )

            Row(modifier = Modifier.fillMaxSize()) {
                destinations.forEach { destination ->
                    NavBarItem(
                        destination = destination,
                        selected = destination == currentDestination,
                        onClick = { onNavigate(destination) },
                        modifier = Modifier.width(itemWidth).fillMaxHeight()
                    )
                }
            }
        }
    }
}

@Composable
private fun NavBarItem(
    destination: NavDestination,
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
        label = "navItemColor"
    )

    Row(
        modifier = modifier
            .clip(CircleShape)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .noRippleClickable(interactionSource, onClick = onClick),
        horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = destination.icon,
            contentDescription = null,
            tint = contentColor,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = stringResource(destination.labelRes),
            style = MaterialTheme.typography.labelMedium.copy(
                fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold
            ),
            color = contentColor,
            maxLines = 1
        )
    }
}
