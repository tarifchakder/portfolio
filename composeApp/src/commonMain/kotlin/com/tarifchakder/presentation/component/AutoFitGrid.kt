package com.tarifchakder.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Column count is derived from the available width, and cells stretch to fill the row - the
 * equivalent of CSS `grid-template-columns: repeat(auto-fit, minmax(minCellWidth, 1fr))`.
 *
 * Built from Rows rather than a lazy grid on purpose: these grids live inside a vertically
 * scrolling page, where a nested lazy grid cannot measure itself.
 */
@Composable
fun <T> AutoFitGrid(
    items: List<T>,
    modifier: Modifier = Modifier,
    minCellWidth: Dp = 240.dp,
    spacing: Dp = 16.dp,
    content: @Composable (T) -> Unit
) {
    BoxWithConstraints(modifier = modifier.fillMaxWidth()) {
        val columns = ((maxWidth + spacing) / (minCellWidth + spacing))
            .toInt()
            .coerceAtLeast(1)

        Column(verticalArrangement = Arrangement.spacedBy(spacing)) {
            items.chunked(columns).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(spacing)
                ) {
                    rowItems.forEach { item ->
                        Box(modifier = Modifier.weight(1f)) { content(item) }
                    }
                    // Keeps the final row's cells the same width as every other row's.
                    repeat(columns - rowItems.size) {
                        Box(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
