package com.tarifchakder.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.presentation.widget.AnimateSkillText
import com.tarifchakder.presentation.widget.IconButtonRow
import com.tarifchakder.presentation.widget.ImageTitleSubtitleCard
import com.tarifchakder.util.noRippleClickable
import com.tarifchakder.util.softShadow
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.rounded_pic

private const val PROFILE_NAME = "Md Tarif Chakder"
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SidebarScreen(
    modifier: Modifier = Modifier,
    showDetails: Boolean,
    showToggle: Boolean,
    onToggleDetails: (() -> Unit)?
) {
    val uriHandler = LocalUriHandler.current
    var showWhatsappSheet by remember { mutableStateOf(false) }

    Box(modifier = modifier.padding(horizontal = 10.dp, vertical = 16.dp)) {
        if (showToggle && onToggleDetails != null) {
            SidebarToggleButton(
                expanded = showDetails,
                onClick = onToggleDetails,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(top = 14.dp, bottom = 14.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(Res.drawable.rounded_pic),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = PROFILE_NAME,
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            )
            AnimateSkillText(modifier = Modifier.padding(top = 10.dp))

            AnimatedVisibility(
                visible = !showToggle || showDetails,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                SidebarDetails(
                    onEmailClick = { uriHandler.openUri("mailto:$EMAIL") },
                    onUaePhoneClick = { uriHandler.openUri("tel:+971589513506") },
                    onIndiaPhoneClick = { uriHandler.openUri("tel:+919804394495") },
                    onWhatsAppClick = { showWhatsappSheet = true },
                    onLocationClick = { uriHandler.openUri(MAP_URL) },
                    onLinkedinClick = { uriHandler.openUri(LINKEDIN_URL) },
                    onGithubClick = { uriHandler.openUri(GITHUB_URL) },
                    onGooglePlayClick = { uriHandler.openUri(GOOGLE_PLAY_URL) },
                    onInstagramClick = { uriHandler.openUri(INSTAGRAM_URL) }
                )
            }
        }
    }

    if (showWhatsappSheet) {
        ModalBottomSheet(
            onDismissRequest = { showWhatsappSheet = false },
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp),
            tonalElevation = 8.dp,
            shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp),
            dragHandle = {
                Box(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .width(44.dp)
                        .height(5.dp)
                        .clip(RoundedCornerShape(percent = 50))
                        .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.7f))
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(2.dp)
                ) {
                    Text(
                        text = "Choose WhatsApp Number",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Select where you want to start chat",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.65f)
                    )
                }

                WhatsappOptionCard(
                    title = "UAE Number",
                    number = UAE_PHONE,
                    onClick = {
                        showWhatsappSheet = false
                        uriHandler.openUri(UAE_WHATSAPP_URL)
                    }
                )

                WhatsappOptionCard(
                    title = "India Number",
                    number = INDIA_PHONE,
                    onClick = {
                        showWhatsappSheet = false
                        uriHandler.openUri(INDIA_WHATSAPP_URL)
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun WhatsappOptionCard(
    title: String,
    number: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .softShadow(MaterialTheme.shapes.large)
            .noRippleClickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 14.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = number,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
            }

            Icon(
                imageVector = Icons.Rounded.Phone,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun SidebarToggleButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    Box(
        modifier = modifier
            .size(38.dp)
            .clip(CircleShape)
            .background(
                color = MaterialTheme.colorScheme.primary.copy(
                    alpha = when {
                        expanded -> 0.14f
                        hovered -> 0.12f
                        else -> 0.08f
                    }
                ),
                shape = CircleShape
            )
            .border(
                border = BorderStroke(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primary.copy(
                        alpha = when {
                            expanded -> 0.32f
                            hovered -> 0.24f
                            else -> 0.18f
                        }
                    )
                ),
                shape = CircleShape
            )
            .noRippleClickable(interactionSource = interactionSource, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = if (expanded) Icons.Rounded.KeyboardArrowUp else Icons.Rounded.KeyboardArrowDown,
            tint = if (expanded) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
            },
            contentDescription = "Toggle Sidebar Details"
        )
    }
}

@Composable
private fun SidebarDetails(
    onEmailClick: () -> Unit,
    onUaePhoneClick: () -> Unit,
    onIndiaPhoneClick: () -> Unit,
    onWhatsAppClick: () -> Unit,
    onLocationClick: () -> Unit,
    onLinkedinClick: () -> Unit,
    onGithubClick: () -> Unit,
    onGooglePlayClick: () -> Unit,
    onInstagramClick: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalDivider(
            modifier = Modifier.padding(30.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
        )

        ImageTitleSubtitleCard(
            icon = Icons.Rounded.Email,
            title = "EMAIL",
            subtitle = EMAIL,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
            onClick = onEmailClick
        )

        ImageTitleSubtitleCard(
            icon = Icons.Rounded.Phone,
            title = "MOBILE (UAE)",
            subtitle = UAE_PHONE,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
            onClick = onUaePhoneClick
        )

        ImageTitleSubtitleCard(
            icon = Icons.Rounded.Phone,
            title = "MOBILE (INDIA)",
            subtitle = INDIA_PHONE,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
            onClick = onIndiaPhoneClick
        )

        ImageTitleSubtitleCard(
            icon = Icons.Rounded.LocationOn,
            title = "LOCATION",
            subtitle = LOCATION,
            modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
            onClick = onLocationClick
        )

        Spacer(Modifier.height(30.dp))

        IconButtonRow(
            onLinkedinClick = onLinkedinClick,
            onGithubLinkedIn = onGithubClick,
            onGooglePlayClick = onGooglePlayClick,
            onInstagramClick = onInstagramClick,
            onWhatsappClick = onWhatsAppClick
        )

        Spacer(Modifier.height(20.dp))
    }
}
