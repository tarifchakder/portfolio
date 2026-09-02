package com.tarifchakder.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.presentation.component.AccentRule
import com.tarifchakder.presentation.component.GlassCard
import com.tarifchakder.presentation.component.GlassPanel
import com.tarifchakder.presentation.component.SecondaryGlassButton
import com.tarifchakder.presentation.widget.AnimateSkillText
import com.tarifchakder.presentation.widget.SocialLinkRow
import com.tarifchakder.theme.LocalGlass
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.contact_email_label
import portfolio.composeapp.generated.resources.contact_location_label
import portfolio.composeapp.generated.resources.contact_mobile_india_label
import portfolio.composeapp.generated.resources.contact_mobile_uae_label
import portfolio.composeapp.generated.resources.profile_contact_details
import portfolio.composeapp.generated.resources.profile_hide_details
import portfolio.composeapp.generated.resources.rounded_pic
import portfolio.composeapp.generated.resources.whatsapp_india_option
import portfolio.composeapp.generated.resources.whatsapp_sheet_subtitle
import portfolio.composeapp.generated.resources.whatsapp_sheet_title
import portfolio.composeapp.generated.resources.whatsapp_uae_option

private const val PROFILE_NAME = "Md Tarif Chakder"
private const val PROFILE_ROLE = "Senior Android Engineer"
private const val EMAIL = "tarifchakdar@gmail.com"
private const val UAE_PHONE = "+971 589513506"
private const val INDIA_PHONE = "+91 9804394495"
private const val UAE_WHATSAPP_URL = "https://wa.me/971589513506"
private const val INDIA_WHATSAPP_URL = "https://wa.me/919804394495"
private const val LOCATION = "United Arab Emirates"
private const val MAP_URL = "https://maps.app.goo.gl/dEgXiL8fcfqwRWgNA"
private const val LINKEDIN_URL = "https://www.linkedin.com/in/tarifchakder"
private const val GITHUB_URL = "https://github.com/tarifchakder"
private const val GOOGLE_PLAY_URL = "https://play.google.com/store/apps/dev?id=6362563028488118131"
private const val INSTAGRAM_URL = "https://www.instagram.com/tarifchakder/"

/**
 * Identity card: avatar, name, rotating specialisms, contact channels and social links.
 *
 * On desktop this is a permanent rail with everything expanded; on smaller screens the contact
 * block collapses behind a toggle so the page opens on the content rather than on metadata.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfilePanel(
    modifier: Modifier = Modifier,
    showDetails: Boolean,
    showToggle: Boolean,
    onToggleDetails: (() -> Unit)?
) {
    val g = LocalGlass.current
    val uriHandler = LocalUriHandler.current
    var showWhatsappSheet by remember { mutableStateOf(false) }

    GlassPanel(modifier = modifier) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Avatar()

            Spacer(Modifier.height(14.dp))

            Text(
                text = PROFILE_NAME,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = g.textPrimary,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = PROFILE_ROLE,
                style = MaterialTheme.typography.labelMedium,
                color = g.textTertiary,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(12.dp))

            AnimateSkillText()

            AnimatedVisibility(
                visible = !showToggle || showDetails,
                enter = fadeIn(tween(220)) + expandVertically(tween(260)),
                exit = fadeOut(tween(160)) + shrinkVertically(tween(220))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(Modifier.height(20.dp))
                    AccentRule()
                    Spacer(Modifier.height(18.dp))

                    ContactList(
                        entries = listOf(
                            ContactEntry(
                                icon = Icons.Rounded.Email,
                                label = stringResource(Res.string.contact_email_label),
                                value = EMAIL,
                                onClick = { uriHandler.openUri("mailto:$EMAIL") }
                            ),
                            ContactEntry(
                                icon = Icons.Rounded.Phone,
                                label = stringResource(Res.string.contact_mobile_uae_label),
                                value = UAE_PHONE,
                                onClick = { uriHandler.openUri("tel:+971589513506") }
                            ),
                            ContactEntry(
                                icon = Icons.Rounded.Phone,
                                label = stringResource(Res.string.contact_mobile_india_label),
                                value = INDIA_PHONE,
                                onClick = { uriHandler.openUri("tel:+919804394495") }
                            ),
                            ContactEntry(
                                icon = Icons.Rounded.LocationOn,
                                label = stringResource(Res.string.contact_location_label),
                                value = LOCATION,
                                onClick = { uriHandler.openUri(MAP_URL) }
                            )
                        )
                    )

                    Spacer(Modifier.height(20.dp))

                    SocialLinkRow(
                        onLinkedinClick = { uriHandler.openUri(LINKEDIN_URL) },
                        onGithubClick = { uriHandler.openUri(GITHUB_URL) },
                        onGooglePlayClick = { uriHandler.openUri(GOOGLE_PLAY_URL) },
                        onInstagramClick = { uriHandler.openUri(INSTAGRAM_URL) },
                        onWhatsappClick = { showWhatsappSheet = true }
                    )
                }
            }

            if (showToggle && onToggleDetails != null) {
                Spacer(Modifier.height(18.dp))
                ExpandToggle(expanded = showDetails, onClick = onToggleDetails)
            }
        }
    }

    if (showWhatsappSheet) {
        ModalBottomSheet(
            onDismissRequest = { showWhatsappSheet = false },
            containerColor = g.base,
            contentColor = g.textPrimary,
            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = 14.dp)
                        .width(46.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(g.strokeBright)
                )
            }
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 22.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(Res.string.whatsapp_sheet_title),
                    style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                    color = g.textPrimary
                )
                Text(
                    text = stringResource(Res.string.whatsapp_sheet_subtitle),
                    style = MaterialTheme.typography.bodySmall,
                    color = g.textTertiary
                )

                Spacer(Modifier.height(2.dp))

                WhatsappOption(
                    title = stringResource(Res.string.whatsapp_uae_option),
                    number = UAE_PHONE,
                    onClick = {
                        showWhatsappSheet = false
                        uriHandler.openUri(UAE_WHATSAPP_URL)
                    }
                )
                WhatsappOption(
                    title = stringResource(Res.string.whatsapp_india_option),
                    number = INDIA_PHONE,
                    onClick = {
                        showWhatsappSheet = false
                        uriHandler.openUri(INDIA_WHATSAPP_URL)
                    }
                )

                Spacer(Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun Avatar() {
    val g = LocalGlass.current

    Box(
        modifier = Modifier
            .size(104.dp)
            // dropShadow, not Modifier.shadow - see glassShadow's note. Here it doubles as an
            // accent-coloured glow rather than a cast shadow, so it has no vertical offset.
            .dropShadow(CircleShape) {
                radius = 26.dp.toPx()
                color = g.accent.copy(alpha = 0.5f)
            }
            .clip(CircleShape)
            .background(g.accentGradient),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(Res.drawable.rounded_pic),
            contentDescription = PROFILE_NAME,
            // The source is a 992x1056 portrait with a rounded-rect alpha mask, not a square.
            // Without Crop, Image's default Fit letterboxes it inside the circle, leaving
            // transparent slivers at the left and right edges and pulling the artwork's own
            // rounded corners into view. Crop fills the circle edge to edge.
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(97.dp)
                .clip(CircleShape)
                .background(g.base)
                .border(1.dp, g.strokeBright, CircleShape)
        )
    }
}

private data class ContactEntry(
    val icon: ImageVector,
    val label: String,
    val value: String,
    val onClick: () -> Unit
)

private val ContactIconSize = 42.dp
private val ContactRowGap = 10.dp

/**
 * Contact channels as individually spaced glass rows.
 *
 * Earlier revisions packed these into one card split by hairline dividers, which read as cramped
 * and cheap: the rules competed with the panel's own edge, and 11dp of vertical padding left the
 * label and value almost touching the neighbouring row. Whitespace separates these instead - each
 * channel is its own small surface with room to breathe, matching the card language used for
 * stats and projects elsewhere on the page.
 */
@Composable
private fun ContactList(entries: List<ContactEntry>) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(ContactRowGap)
    ) {
        entries.forEach { entry -> ContactRow(entry) }
    }
}

@Composable
private fun ContactRow(entry: ContactEntry) {
    val g = LocalGlass.current

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = entry.onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(ContactIconSize)
                    .clip(MaterialTheme.shapes.small)
                    .background(g.accentSoft),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = entry.icon,
                    contentDescription = null,
                    tint = g.accent,
                    modifier = Modifier.size(19.dp)
                )
            }

            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = entry.label,
                    style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.4.sp),
                    color = g.textTertiary
                )
                Text(
                    text = entry.value,
                    style = MaterialTheme.typography.labelMedium,
                    color = g.textPrimary,
                    fontSize = 13.5.sp,
                    maxLines = 1
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Rounded.OpenInNew,
                contentDescription = null,
                tint = g.textTertiary.copy(alpha = 0.55f),
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
private fun WhatsappOption(title: String, number: String, onClick: () -> Unit) {
    val g = LocalGlass.current

    GlassCard(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 14.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelLarge,
                    color = g.textPrimary
                )
                Text(
                    text = number,
                    style = MaterialTheme.typography.bodySmall,
                    color = g.textTertiary
                )
            }
            Icon(
                imageVector = Icons.Rounded.Phone,
                contentDescription = null,
                tint = g.accent,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

/**
 * Disclosure control for the contact block. A labelled pill rather than a bare corner chevron:
 * the corner is where the floating theme toggle lives, and "Contact details" states what opens.
 */
@Composable
private fun ExpandToggle(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotation by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f,
        animationSpec = tween(280),
        label = "expandArrow"
    )

    SecondaryGlassButton(
        text = stringResource(
            if (expanded) Res.string.profile_hide_details else Res.string.profile_contact_details
        ),
        onClick = onClick,
        modifier = modifier,
        trailing = {
            Icon(
                imageVector = Icons.Rounded.KeyboardArrowDown,
                contentDescription = null,
                tint = LocalGlass.current.textSecondary,
                modifier = Modifier.size(18.dp).rotate(rotation)
            )
        }
    )
}
