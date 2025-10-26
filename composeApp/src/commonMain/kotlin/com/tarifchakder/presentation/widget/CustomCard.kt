package com.tarifchakder.presentation.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
    cornerSize: RoundedCornerShape = RoundedCornerShape(20.dp),
    topCornerExtra: Boolean = false,
    bottomCornerExtra: Boolean = false,
    elevation: Dp = 0.0.dp,
    color: Color = MaterialTheme.colorScheme.surfaceColorAtElevation(0.2.dp),
    onClick: () -> Unit = {},
    content: @Composable() (() -> Unit)
) {
    val shape = if (topCornerExtra) {
        RoundedCornerShape(
            topStart = 15.dp,
            topEnd = 15.dp,
            bottomEnd = 5.dp,
            bottomStart = 5.dp
        )
    } else if (bottomCornerExtra) {
        RoundedCornerShape(
            topStart = 5.dp,
            topEnd = 5.dp,
            bottomEnd = 15.dp,
            bottomStart = 15.dp
        )
    } else {
        cornerSize
    }

    Card(
        modifier = modifier.wrapContentSize(),
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(elevation),
    ) {
        Box(
            modifier = modifier.clickable { onClick() },
            content = { content() }
        )
    }
}