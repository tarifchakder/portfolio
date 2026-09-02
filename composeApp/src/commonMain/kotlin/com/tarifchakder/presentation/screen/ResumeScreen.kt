package com.tarifchakder.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material.icons.rounded.Interests
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.platform.rememberPdfDownloader
import com.tarifchakder.presentation.component.AccentRule
import com.tarifchakder.presentation.component.GlassCard
import com.tarifchakder.presentation.component.GlassChip
import com.tarifchakder.presentation.component.GlassPanel
import com.tarifchakder.presentation.component.PrimaryGlassButton
import com.tarifchakder.presentation.component.SectionPanel
import com.tarifchakder.theme.LocalGlass
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.comera_pay
import portfolio.composeapp.generated.resources.geidea
import portfolio.composeapp.generated.resources.network_international
import portfolio.composeapp.generated.resources.oma_emirates
import portfolio.composeapp.generated.resources.resume_download_cv
import portfolio.composeapp.generated.resources.resume_education_title
import portfolio.composeapp.generated.resources.resume_experience_title
import portfolio.composeapp.generated.resources.resume_skills_title
import portfolio.composeapp.generated.resources.resume_tagline
import portfolio.composeapp.generated.resources.resume_title
import portfolio.composeapp.generated.resources.resume_years_chip

private const val RESUME_URL = "https://raw.githubusercontent.com/tarifchakder/portfolio/main/composeApp/src/commonMain/composeResources/files/Md_Tarif_Chakder_Resume.pdf"

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

private val EXPERIENCE = listOf(
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

private val EDUCATION = listOf(
    ResumeEntry(
        company = "Budge Budge Institute of Technology (MAKAUT)",
        role = "Bachelor of Technology - Computer Science and Technology",
        period = "2015 - 2019"
    )
)

private val SKILL_GROUPS = listOf(
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

@Composable
fun ResumeScreen(breakpoint: WindowSizeClass) {
    val uriHandler = LocalUriHandler.current
    val pdfDownloader = rememberPdfDownloader()
    val coroutineScope = rememberCoroutineScope()
    val isWide = breakpoint == WindowSizeClass.Expanded
    val isCompact = breakpoint == WindowSizeClass.Compact

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        ResumeHeader(
            compact = isCompact,
            onDownloadClick = {
                coroutineScope.launch {
                    try {
                        val bytes = Res.readBytes("files/Md_Tarif_Chakder_Resume.pdf")
                        pdfDownloader.openOrDownloadPdf("Md_Tarif_Chakder_Resume.pdf", bytes)
                    } catch (e: Exception) {
                        e.printStackTrace()
                        uriHandler.openUri(RESUME_URL)
                    }
                }
            }
        )

        if (isWide) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Column(
                    modifier = Modifier.weight(1.6f),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    ExperienceSection(compact = false)
                    EducationSection(compact = false)
                }
                Column(modifier = Modifier.weight(1f)) {
                    SkillsSection()
                }
            }
        } else {
            ExperienceSection(compact = isCompact)
            EducationSection(compact = isCompact)
            SkillsSection()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ResumeHeader(compact: Boolean, onDownloadClick: () -> Unit) {
    val g = LocalGlass.current

    GlassPanel(modifier = Modifier.fillMaxWidth()) {
        FlowRow(
            modifier = Modifier.fillMaxWidth().padding(if (compact) 22.dp else 28.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f, fill = false).widthIn(min = 220.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = stringResource(Res.string.resume_title),
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                        color = g.textPrimary
                    )
                    GlassChip(text = stringResource(Res.string.resume_years_chip), accent = true)
                }
                AccentRule()
                Text(
                    text = stringResource(Res.string.resume_tagline),
                    style = MaterialTheme.typography.bodyMedium,
                    color = g.textSecondary
                )
            }

            PrimaryGlassButton(
                text = stringResource(Res.string.resume_download_cv),
                icon = Icons.Rounded.Download,
                onClick = onDownloadClick
            )
        }
    }
}

@Composable
private fun ExperienceSection(compact: Boolean = false) {
    SectionPanel(
        title = stringResource(Res.string.resume_experience_title),
        icon = Icons.Rounded.Work,
        modifier = Modifier.fillMaxWidth()
    ) {
        Timeline(EXPERIENCE, compact = compact)
    }
}

@Composable
private fun EducationSection(compact: Boolean = false) {
    SectionPanel(
        title = stringResource(Res.string.resume_education_title),
        icon = Icons.Rounded.School,
        modifier = Modifier.fillMaxWidth()
    ) {
        Timeline(EDUCATION, compact = compact)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun SkillsSection() {
    val g = LocalGlass.current

    SectionPanel(
        title = stringResource(Res.string.resume_skills_title),
        icon = Icons.Rounded.Interests,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
            SKILL_GROUPS.forEach { group ->
                GlassCard(modifier = Modifier.fillMaxWidth(), shape = MaterialTheme.shapes.large) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = group.category,
                            style = MaterialTheme.typography.labelSmall.copy(letterSpacing = 1.sp),
                            color = g.textTertiary
                        )
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            group.skills.forEach { skill -> GlassChip(text = skill, accent = true) }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Timeline(entries: List<ResumeEntry>, compact: Boolean = false) {
    Column {
        entries.forEachIndexed { index, entry ->
            TimelineItem(
                entry = entry,
                showConnector = index != entries.lastIndex,
                compact = compact
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun TimelineItem(entry: ResumeEntry, showConnector: Boolean, compact: Boolean = false) {
    val g = LocalGlass.current

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier.width(26.dp).fillMaxHeight(),
            contentAlignment = Alignment.TopCenter
        ) {
            TimelineDot()
            if (showConnector) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .fillMaxHeight()
                        .padding(top = 22.dp)
                        .background(g.strokeBright)
                )
            }
        }

        GlassCard(
            modifier = Modifier
                .weight(1f)
                .padding(start = 12.dp, bottom = if (showConnector) 16.dp else 0.dp),
            shape = MaterialTheme.shapes.large
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (compact) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            if (entry.logoRes != null || entry.logoInitials != null) {
                                CompanyLogo(entry.logoRes, entry.logoInitials, entry.logoColor)
                            }
                            Text(
                                text = entry.company,
                                style = MaterialTheme.typography.labelLarge,
                                color = g.textPrimary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f)
                            )
                        }
                        if (entry.period.isNotBlank()) {
                            GlassChip(text = entry.period, accent = true)
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.weight(1f, fill = false).padding(end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            if (entry.logoRes != null || entry.logoInitials != null) {
                                CompanyLogo(entry.logoRes, entry.logoInitials, entry.logoColor)
                            }
                            Text(
                                text = entry.company,
                                style = MaterialTheme.typography.labelLarge,
                                color = g.textPrimary,
                                maxLines = 2
                            )
                        }
                        if (entry.period.isNotBlank()) {
                            GlassChip(text = entry.period, accent = true)
                        }
                    }
                }

                Text(
                    text = entry.role,
                    style = MaterialTheme.typography.bodyMedium,
                    color = g.textSecondary
                )

                val meta = listOfNotNull(entry.employmentType, entry.location, entry.workMode)
                if (meta.isNotEmpty()) {
                    Text(
                        text = meta.joinToString("  ·  "),
                        style = MaterialTheme.typography.bodySmall,
                        color = g.textTertiary
                    )
                }

                if (entry.highlights.isNotEmpty()) {
                    Column(
                        modifier = Modifier.padding(top = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        entry.highlights.forEach { HighlightBullet(it) }
                    }
                }

                if (entry.techStack.isNotEmpty()) {
                    FlowRow(
                        modifier = Modifier.padding(top = 6.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        entry.techStack.forEach { GlassChip(text = it) }
                    }
                }
            }
        }
    }
}

@Composable
private fun CompanyLogo(logoRes: DrawableResource?, initials: String?, color: Color?) {
    val g = LocalGlass.current

    if (logoRes != null) {
        Image(
            painter = painterResource(logoRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(30.dp).clip(RoundedCornerShape(8.dp))
        )
    } else if (initials != null) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(color ?: g.accent),
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
    val g = LocalGlass.current

    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .padding(top = 7.dp)
                .size(5.dp)
                .clip(CircleShape)
                .background(g.accent)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = g.textSecondary,
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
private fun TimelineDot() {
    val g = LocalGlass.current

    Box(
        modifier = Modifier
            .padding(top = 4.dp)
            .size(16.dp)
            .clip(CircleShape)
            .background(g.accentSoft),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .clip(CircleShape)
                .background(g.accentGradient)
        )
    }
}
