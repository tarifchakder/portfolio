package com.tarifchakder.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Download
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.WindowSizeClass

private const val RESUME_URL = "https://example.com/resume.pdf"

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ResumeScreen(breakpoint: WindowSizeClass) {
    val uriHandler = LocalUriHandler.current
    val isCompact = breakpoint == WindowSizeClass.Compact

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text(
            text = "Resume",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(percent = 50))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
        )

        FilledTonalButton(onClick = { uriHandler.openUri(RESUME_URL) }) {
            Icon(imageVector = Icons.Rounded.Download, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Download CV")
        }

        if (isCompact) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ResumeSectionCard(title = "Experience") {
                    TimelineItem(
                        period = "2022 - Present",
                        title = "Senior Android Engineer",
                        subtitle = "Banking and Fintech",
                        description = "Building secure, scalable mobile apps with Kotlin and Kotlin Multiplatform."
                    )
                    TimelineItem(
                        period = "2019 - 2022",
                        title = "Android Engineer",
                        subtitle = "Product Engineering",
                        description = "Delivered high-performance Android features and improved app architecture."
                    )
                }

                ResumeSectionCard(title = "Education") {
                    TimelineItem(
                        period = "2015 - 2019",
                        title = "B.Sc. in Computer Science",
                        subtitle = "University",
                        description = "Focused on software engineering, data structures, and distributed systems."
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ResumeSectionCard(
                    title = "Experience",
                    modifier = Modifier.weight(1f)
                ) {
                    TimelineItem(
                        period = "2022 - Present",
                        title = "Senior Android Engineer",
                        subtitle = "Banking and Fintech",
                        description = "Building secure, scalable mobile apps with Kotlin and Kotlin Multiplatform."
                    )
                    TimelineItem(
                        period = "2019 - 2022",
                        title = "Android Engineer",
                        subtitle = "Product Engineering",
                        description = "Delivered high-performance Android features and improved app architecture."
                    )
                }

                ResumeSectionCard(
                    title = "Education",
                    modifier = Modifier.weight(1f)
                ) {
                    TimelineItem(
                        period = "2015 - 2019",
                        title = "B.Sc. in Computer Science",
                        subtitle = "University",
                        description = "Focused on software engineering, data structures, and distributed systems."
                    )
                }
            }
        }

        ResumeSectionCard(title = "Skills") {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "Kotlin",
                    "KMP",
                    "Android",
                    "Compose",
                    "Coroutines",
                    "Clean Architecture",
                    "Unit Testing",
                    "CI/CD"
                ).forEach { skill ->
                    SkillPill(skill)
                }
            }
        }
    }
}

@Composable
private fun ResumeSectionCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.45f))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                    color = MaterialTheme.colorScheme.onSurface
                )
                content()
            }
        )
    }
}

@Composable
private fun TimelineItem(
    period: String,
    title: String,
    subtitle: String,
    description: String
) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(
            text = period,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f)
        )
        Spacer(Modifier.height(4.dp))
    }
}

@Composable
private fun SkillPill(label: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(999.dp))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.25f)),
                RoundedCornerShape(999.dp)
            )
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
