package com.tarifchakder.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.util.softShadow
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.comera_pay
import portfolio.composeapp.generated.resources.geidea
import portfolio.composeapp.generated.resources.network_international
import portfolio.composeapp.generated.resources.oma_emirates

private const val RESUME_URL = "https://example.com/resume.pdf"
private const val TAGLINE = "A snapshot of my experience, education, and core skills - " +
    "download the full CV for the complete picture."

data class ResumeEntry(
    val company: String,
    val role: String,
    val period: String,
    val highlights: List<String> = emptyList(),
    val techStack: List<String> = emptyList(),
    val logoInitials: String? = null,
    val logoColor: Color? = null,
    val logoRes: DrawableResource? = null,
    val employmentType: String? = null,
    val location: String? = null,
    val workMode: String? = null
)

data class SkillGroup(
    val category: String,
    val skills: List<String>
)

@Composable
fun ResumeScreen(breakpoint: WindowSizeClass) {
    val uriHandler = LocalUriHandler.current

    val experienceEntries = listOf(
        ResumeEntry(
            company = "Comera Pay",
            role = "Senior Software Engineer - Android",
            period = "2025 - Present",
            highlights = listOf(
                "Building the Android app for a Central Bank of UAE-regulated digital payments platform covering wallets, multicurrency VISA cards, and remittance.",
                "Developing secure payment flows for the payment gateway, POS, and QR code payment products, aligned with PCI DSS compliance requirements.",
                "Sharing business logic across platforms with Kotlin Multiplatform to speed up feature delivery for a fast-growing fintech product line."
            ),
            techStack = listOf("Kotlin", "KMP", "Compose", "Coroutines", "Security"),
            logoInitials = "CP",
            logoColor = Color(0xFF0A5AD1),
            logoRes = Res.drawable.comera_pay,
            employmentType = "Full-time",
            location = "Abu Dhabi, UAE",
            workMode = "Onsite"
        ),
        ResumeEntry(
            company = "Network International",
            role = "Software Developer",
            period = "2024 - 2025",
            highlights = listOf(
                "Worked on N-Genius, a POS (point-of-sale) application handling in-person card transactions for merchants.",
                "Implemented XMPP and gRPC communication for real-time messaging and device-to-server data exchange in the POS transaction flow.",
                "Integrated and tested payment terminals from Verifone and Ingenico, and worked with ePOS devices across multiple card schemes."
            ),
            techStack = listOf("Kotlin", "Android", "XMPP", "gRPC", "POS Devices"),
            logoInitials = "NI",
            logoColor = Color(0xFF00A19A),
            logoRes = Res.drawable.network_international,
            employmentType = "Full-time",
            location = "Dubai, UAE",
            workMode = "Hybrid"
        ),
        ResumeEntry(
            company = "Geidea",
            role = "Software Engineer - Android",
            period = "2023 - 2024",
            highlights = listOf(
                "Built a modern POS application for the Saudi Arabia (KSA) payments market using the latest Kotlin-based architecture.",
                "Implemented SDKs for POS app communication, enabling reliable data exchange between the app and payment terminals.",
                "Implemented ISO 8583 message handling for standardized financial transaction processing."
            ),
            techStack = listOf("Kotlin", "Android", "ISO 8583", "POS SDKs", "Payments"),
            logoInitials = "GD",
            logoColor = Color(0xFF7B4FE0),
            logoRes = Res.drawable.geidea,
            employmentType = "Full-time",
            location = "Bangalore, India",
            workMode = "Hybrid"
        ),
        ResumeEntry(
            company = "Oma Emirates LLC",
            role = "Software Engineer - Android",
            period = "2022 - 2023",
            highlights = listOf(
                "Built a modern POS application for the UAE payments market using the latest Kotlin-based architecture.",
                "Implemented SDKs for POS app communication, enabling reliable data exchange between the app and payment terminals."
            ),
            techStack = listOf("Kotlin", "Android", "POS SDKs", "Payments"),
            logoInitials = "OE",
            logoColor = Color(0xFFE0654F),
            logoRes = Res.drawable.oma_emirates,
            employmentType = "Full-time",
            location = "United Arab Emirates",
            workMode = "Onsite"
        )
    )

    val educationEntries = listOf(
        ResumeEntry(
            company = "Budge Budge Institute of Technology (MAKAUT)",
            role = "Bachelor of Technology - Computer Science and Technology",
            period = "2015 - 2019"
        )
    )

    val skillGroups = listOf(
        SkillGroup(
            "Languages & Frameworks",
            listOf("Kotlin", "Android", "KMP", "Compose", "Java", "Coroutines & Flow")
        ),
        SkillGroup(
            "Payments & POS",
            listOf("ISO 8583", "POS SDKs", "XMPP", "gRPC", "Verifone", "Ingenico", "Card Schemes")
        ),
        SkillGroup(
            "Architecture",
            listOf("Clean Architecture", "MVVM", "Security")
        ),
        SkillGroup(
            "Tooling & Practices",
            listOf("Unit Testing", "CI/CD", "Git")
        )
    )

    val isWide = breakpoint == WindowSizeClass.Expanded

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        ResumePageHeader(onDownloadClick = { uriHandler.openUri(RESUME_URL) })

        if (isWide) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1.6f),
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    ResumeSection(title = "Experience", icon = Icons.Rounded.Work) {
                        ResumeTimeline(entries = experienceEntries)
                    }
                    ResumeSection(title = "Education", icon = Icons.Rounded.School) {
                        ResumeTimeline(entries = educationEntries)
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(28.dp)
                ) {
                    SkillsSection(skillGroups)
                }
            }
        } else {
            ResumeSection(title = "Experience", icon = Icons.Rounded.Work) {
                ResumeTimeline(entries = experienceEntries)
            }
            ResumeSection(title = "Education", icon = Icons.Rounded.School) {
                ResumeTimeline(entries = educationEntries)
            }
            SkillsSection(skillGroups)
        }
    }
}

@Composable
private fun ResumePageHeader(onDownloadClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(
                    text = "Resume",
                    style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(999.dp))
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
                        .padding(horizontal = 10.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = "7+ years",
                        style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
            Text(
                text = TAGLINE,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
        }

        Button(
            onClick = onDownloadClick,
            modifier = Modifier.softShadow(RoundedCornerShape(999.dp), elevation = 6.dp, alpha = 0.22f),
            shape = RoundedCornerShape(999.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            ),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.Download,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Download CV", fontWeight = FontWeight.SemiBold)
        }
    }
}

@Composable
private fun ResumeSection(
    title: String,
    icon: ImageVector?,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeader(title = title, icon = icon)
        content()
    }
}

@Composable
private fun SectionHeader(title: String, icon: ImageVector?) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        if (icon != null) {
            Box(
                modifier = Modifier
                    .size(34.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SkillsSection(skillGroups: List<SkillGroup>) {
    ResumeSection(title = "Skills", icon = null) {
        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
            skillGroups.forEach { group ->
                Card(
                    modifier = Modifier.fillMaxWidth().softShadow(MaterialTheme.shapes.large),
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = group.category,
                            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.SemiBold),
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            group.skills.forEach { skill -> SkillChip(skill) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SkillChip(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun ResumeTimeline(entries: List<ResumeEntry>) {
    Column {
        entries.forEachIndexed { index, entry ->
            ResumeTimelineItem(
                entry = entry,
                showConnector = index != entries.lastIndex
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ResumeTimelineItem(
    entry: ResumeEntry,
    showConnector: Boolean
) {
    val railWidth = 28.dp
    val dotRadius = 9.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .width(railWidth)
                .fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            TimelineDot()
            if (showConnector) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .padding(top = dotRadius + 4.dp)
                        .background(MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
                )
            }
        }

        Card(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, bottom = if (showConnector) 16.dp else 0.dp)
                .softShadow(MaterialTheme.shapes.large),
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp)
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Box(
                    modifier = Modifier
                        .width(4.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.6f))
                )
                Column(
                    modifier = Modifier.weight(1f).padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.weight(1f, fill = false),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            if (entry.logoRes != null || entry.logoInitials != null) {
                                CompanyLogo(
                                    logoRes = entry.logoRes,
                                    initials = entry.logoInitials,
                                    color = entry.logoColor
                                )
                            }
                            Text(
                                text = entry.company,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
                                color = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.weight(1f, fill = false)
                            )
                        }
                        if (entry.period.isNotBlank()) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(999.dp))
                                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                                    .padding(horizontal = 10.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = entry.period,
                                    style = MaterialTheme.typography.labelMedium,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                    Text(
                        text = entry.role,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Medium),
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.9f)
                    )

                    val meta = listOfNotNull(entry.employmentType, entry.location, entry.workMode)
                    if (meta.isNotEmpty()) {
                        Text(
                            text = meta.joinToString(" · "),
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                        )
                    }

                    if (entry.highlights.isNotEmpty()) {
                        Column(
                            modifier = Modifier.padding(top = 4.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            entry.highlights.forEach { highlight ->
                                HighlightBullet(highlight)
                            }
                        }
                    }

                    if (entry.techStack.isNotEmpty()) {
                        FlowRow(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            entry.techStack.forEach { tech -> TechTag(tech) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanyLogo(logoRes: DrawableResource?, initials: String?, color: Color?) {
    if (logoRes != null) {
        Image(
            painter = painterResource(logoRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(6.dp))
        )
    } else if (initials != null) {
        Box(
            modifier = Modifier
                .size(28.dp)
                .clip(RoundedCornerShape(6.dp))
                .background(color ?: MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials.take(2).uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold),
                color = Color.White
            )
        }
    }
}

@Composable
private fun HighlightBullet(text: String) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = "•",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.width(10.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun TechTag(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.surfaceColorAtElevation(3.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
    }
}

@Composable
private fun TimelineDot(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(top = 2.dp)
            .size(14.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.18f)),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}
