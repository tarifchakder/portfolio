package com.tarifchakder.presentation.widget

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.NavDestination
import com.tarifchakder.util.noRippleClickable

@Composable
fun AdaptiveNavigationBar(
    modifier: Modifier = Modifier,
    currentDestination: NavDestination,
    onNavigate: (NavDestination) -> Unit
) {
    NavigationBar(
        modifier = modifier.height(80.dp).padding(horizontal = 8.dp),
        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(2.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            NavDestination.entries.forEach { destination ->
                val selected = currentDestination == destination
                val interactionSource = remember { MutableInteractionSource() }
                val hovered by interactionSource.collectIsHoveredAsState()
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .noRippleClickable(interactionSource = interactionSource) {
                            onNavigate(destination)
                        },
                    shape = MaterialTheme.shapes.extraLarge,
                    color = if (selected) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.14f)
                    } else if (hovered) {
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.08f)
                    } else {
                        Color.Transparent
                    },
                    border = if (selected) {
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.32f))
                    } else if (hovered) {
                        BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.18f))
                    } else {
                        null
                    }
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                    ) {
                        val textColor = if (selected) {
                            MaterialTheme.colorScheme.primary
                        } else if (hovered) {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                        } else {
                            MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                        }

                        val fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium

                        Text(
                            text = destination.label,
                            color = textColor,
                            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = fontWeight),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}
