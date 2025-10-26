package com.tarifchakder.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier


fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier =
    this.clickable(
        indication = null,
        interactionSource = MutableInteractionSource(),
        onClick = onClick
    )

fun String.urlEncode(): String {
    return this.replace(" ", "%20")
        .replace(",", "%2C")
        .replace(":", "%3A")
        .replace("/", "%2F")
}