package com.tarifchakder.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Android
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.material.icons.rounded.PersonOutline
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.presentation.component.AccentRule
import com.tarifchakder.presentation.component.AutoFitGrid
import com.tarifchakder.presentation.component.GlassCard
import com.tarifchakder.presentation.component.GlassChip
import com.tarifchakder.presentation.component.GlassPanel
import com.tarifchakder.presentation.component.PrimaryGlassButton
import com.tarifchakder.presentation.component.SecondaryGlassButton
import com.tarifchakder.presentation.component.SectionPanel
import com.tarifchakder.theme.LocalGlass
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.about_description
import portfolio.composeapp.generated.resources.about_get_in_touch
import portfolio.composeapp.generated.resources.about_hero_greeting
import portfolio.composeapp.generated.resources.about_hero_subtitle
import portfolio.composeapp.generated.resources.about_hero_tagline
import portfolio.composeapp.generated.resources.about_location_chip
import portfolio.composeapp.generated.resources.about_me_title
import portfolio.composeapp.generated.resources.about_view_github
import portfolio.composeapp.generated.resources.about_what_i_do_title

private const val EMAIL = "tarifchakdar@gmail.com"
private const val GITHUB_URL = "https://github.com/tarifchakder"

private data class Focus(val icon: ImageVector, val title: String, val body: String)

private val FOCUS_AREAS = listOf(
    Focus(
        Icons.Rounded.Android,
        "Android Engineering",
        "Kotlin and Jetpack Compose apps built for performance, with native components via the NDK where it counts."
    ),
    Focus(
        Icons.Rounded.Layers,
        "Kotlin Multiplatform",
        "Shared business logic across Android, iOS, desktop and web so features ship once instead of three times."
    ),
    Focus(
        Icons.Rounded.Security,
        "Payments & Security",
        "ISO 8583 messaging, POS SDK integrations and payment flows aligned with PCI DSS requirements."
    )
)

@Composable
fun AboutScreen(breakpoint: WindowSizeClass) {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Hero(
            breakpoint = breakpoint,
            onEmailClick = { uriHandler.openUri("mailto:$EMAIL") },
            onGithubClick = { uriHandler.openUri(GITHUB_URL) }
        )

        SectionPanel(
            title = stringResource(Res.string.about_me_title),
            icon = Icons.Rounded.PersonOutline,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(Res.string.about_description),
                style = MaterialTheme.typography.bodyLarge,
                color = LocalGlass.current.textSecondary
            )
        }

        SectionPanel(
            title = stringResource(Res.string.about_what_i_do_title),
            icon = Icons.Rounded.Bolt,
            modifier = Modifier.fillMaxWidth()
        ) {
            AutoFitGrid(items = FOCUS_AREAS, minCellWidth = 230.dp) { focus ->
                FocusCard(focus)
            }
        }
    }
}

@Composable
private fun Hero(
    breakpoint: WindowSizeClass,
    onEmailClick: () -> Unit,
    onGithubClick: () -> Unit
) {
    val g = LocalGlass.current
    val compact = breakpoint == WindowSizeClass.Compact

    GlassPanel(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.fillMaxWidth().padding(if (compact) 22.dp else 32.dp)) {
            GlassChip(text = stringResource(Res.string.about_location_chip), accent = true)

            Spacer(Modifier.height(18.dp))

            Text(
                text = stringResource(Res.string.about_hero_greeting),
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = if (compact) 28.sp else 38.sp,
                    lineHeight = if (compact) 34.sp else 46.sp
                ),
                color = g.textPrimary
            )

            Spacer(Modifier.height(6.dp))

            // Gradient-filled type: the one place the brand ramp is used on text, so it reads as
            // the page's focal point rather than decoration.
            Text(
                text = stringResource(Res.string.about_hero_subtitle),
                style = TextStyle(
                    brush = g.accentGradient,
                    fontWeight = FontWeight.Bold,
                    fontSize = if (compact) 20.sp else 26.sp,
                    lineHeight = if (compact) 28.sp else 34.sp,
                    fontFamily = MaterialTheme.typography.headlineMedium.fontFamily
                )
            )

            Spacer(Modifier.height(16.dp))
            AccentRule()
            Spacer(Modifier.height(16.dp))

            Text(
                text = stringResource(Res.string.about_hero_tagline),
                style = MaterialTheme.typography.bodyLarge,
                color = g.textSecondary
            )

            Spacer(Modifier.height(24.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                PrimaryGlassButton(
                    text = stringResource(Res.string.about_get_in_touch),
                    icon = Icons.Rounded.Email,
                    onClick = onEmailClick
                )
                SecondaryGlassButton(
                    text = stringResource(Res.string.about_view_github),
                    onClick = onGithubClick
                )
            }
        }
    }
}

@Composable
private fun FocusCard(focus: Focus) {
    val g = LocalGlass.current

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(PaddingValues(18.dp)),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(MaterialTheme.shapes.small)
                    .background(g.accentSoft),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = focus.icon,
                    contentDescription = null,
                    tint = g.accent,
                    modifier = Modifier.size(21.dp)
                )
            }
            Text(
                text = focus.title,
                style = MaterialTheme.typography.labelLarge,
                color = g.textPrimary
            )
            Text(
                text = focus.body,
                style = MaterialTheme.typography.bodySmall,
                color = g.textTertiary
            )
        }
    }
}
