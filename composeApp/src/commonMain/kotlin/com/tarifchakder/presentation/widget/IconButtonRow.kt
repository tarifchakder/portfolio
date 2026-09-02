package com.tarifchakder.presentation.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.tarifchakder.presentation.component.GlassIconButton
import com.tarifchakder.theme.LocalGlass
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.github
import portfolio.composeapp.generated.resources.googleplay
import portfolio.composeapp.generated.resources.instagram
import portfolio.composeapp.generated.resources.linkedin
import portfolio.composeapp.generated.resources.whatsapp

/** Social links as a row of small glass buttons that lift on hover. */
@Composable
fun SocialLinkRow(
    modifier: Modifier = Modifier,
    onLinkedinClick: () -> Unit = {},
    onGithubClick: () -> Unit = {},
    onGooglePlayClick: () -> Unit = {},
    onInstagramClick: () -> Unit = {},
    onWhatsappClick: () -> Unit = {},
) {
    val g = LocalGlass.current

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SocialButton(Res.drawable.linkedin, "LinkedIn", onLinkedinClick)
        // The GitHub mark ships as flat black artwork, so it has to follow the ink colour.
        SocialButton(Res.drawable.github, "GitHub", onGithubClick, tint = ColorFilter.tint(g.textPrimary))
        SocialButton(Res.drawable.googleplay, "Google Play", onGooglePlayClick)
        SocialButton(Res.drawable.instagram, "Instagram", onInstagramClick)
        SocialButton(Res.drawable.whatsapp, "WhatsApp", onWhatsappClick)
    }
}

@Composable
private fun SocialButton(
    resource: DrawableResource,
    contentDescription: String,
    onClick: () -> Unit,
    tint: ColorFilter? = null
) {
    GlassIconButton(onClick = onClick, size = 44.dp) {
        Image(
            painter = painterResource(resource),
            contentDescription = contentDescription,
            modifier = Modifier.size(21.dp),
            colorFilter = tint
        )
    }
}
