package com.tarifchakder.presentation.widget

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp

@Composable
fun IconButtonRow(
    modifier: Modifier = Modifier,
    onEmailClick: () -> Unit = {},
    onPhoneClick: () -> Unit = {},
    onLocationClick: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        IconButton(onClick = onEmailClick, modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)) {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email"
            )
        }

        IconButton(onClick = onPhoneClick) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Phone"
            )
        }

        IconButton(onClick = onLocationClick) {
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription = "Location"
            )
        }
    }
}