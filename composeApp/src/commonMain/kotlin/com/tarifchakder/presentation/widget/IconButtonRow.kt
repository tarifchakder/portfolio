package com.tarifchakder.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import com.tarifchakder.util.noRippleClickable
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.github
import portfolio.composeapp.generated.resources.googleplay
import portfolio.composeapp.generated.resources.instagram
import portfolio.composeapp.generated.resources.linkedin

@Composable
fun IconButtonRow(
    modifier: Modifier = Modifier,
    onLinkedinClick: () -> Unit = {},
    onGithubLinkedIn: () -> Unit = {},
    onGooglePlayClick: () -> Unit = {},
    onInstagramClick: () -> Unit = {},
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Image(
            painter = painterResource(Res.drawable.linkedin),
            contentDescription = "Linkedin",
            modifier = Modifier.size(25.dp).pointerHoverIcon(PointerIcon.Hand)
                .noRippleClickable(onClick = onLinkedinClick)
        )

        Image(
            painter = painterResource(Res.drawable.github),
            contentDescription = "Github",
            modifier = Modifier.size(25.dp).pointerHoverIcon(PointerIcon.Hand)
                .noRippleClickable(onClick = onGithubLinkedIn),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onSurface)
        )

        Image(
            painter = painterResource(Res.drawable.googleplay),
            contentDescription = "GooglePlay",
            modifier = Modifier.size(25.dp).pointerHoverIcon(PointerIcon.Hand)
                .noRippleClickable(onClick = onGooglePlayClick)
        )

        Image(
            painter = painterResource(Res.drawable.instagram),
            contentDescription = "Instagram",
            modifier = Modifier.size(25.dp).pointerHoverIcon(PointerIcon.Hand)
                .noRippleClickable(onClick = onInstagramClick)
        )
    }
}