package com.tarifchakder.portfolio.sections.header

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeaderButton(modifier: Modifier = Modifier, text: String,onClick: () -> Unit = {}) {
    TextButton(
        onClick = {
            onClick()
        },
        modifier = modifier.padding(horizontal = 4.dp)
    ) {
        Text(text, color = Color.Black)
    }
}