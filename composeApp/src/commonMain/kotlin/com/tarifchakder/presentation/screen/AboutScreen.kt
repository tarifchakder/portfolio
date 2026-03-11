package com.tarifchakder.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.SmartToy
import androidx.compose.material.icons.rounded.Web
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.WindowSizeClass

@Composable
fun AboutScreen(breakpoint: WindowSizeClass) {
    val sectionSpacing = 16.dp

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(sectionSpacing)
    ) {
        Text(
            text = "About Me",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colorScheme.onSurface
        )
        Box(
            modifier = Modifier
                .width(50.dp)
                .height(4.dp)
                .clip(RoundedCornerShape(percent = 50))
                .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f))
        )
        Text(
            text = "Senior Android Engineer with 7+ years of experience building secure, scalable mobile applications, with a strong focus on banking and fintech platforms.\n\n" +
                "I specialize in developing high-performance Android applications and cross-platform solutions using Kotlin and Kotlin Multiplatform (KMP). My experience includes building secure financial systems, integrating native Android components (NDK), and designing scalable mobile architectures that support demanding production environments.\n\n" +
                "I am particularly focused on delivering secure, reliable, and high-performance mobile solutions for financial services, with deep expertise in mobile security, performance optimization, and cross-platform architecture.\n\n" +
                "Beyond the technical domain, I approach engineering with a mindset centered on discipline, clarity, and continuous refinement. I am driven by curiosity and a systems-oriented perspective-constantly exploring how complex components interact to create efficient, resilient solutions.\n\n" +
                "Rather than focusing solely on milestones, I value the consistent practice of improvement: refining processes, optimizing systems, and solving problems through objective reasoning and structured thinking.\n\n" +
                "I believe meaningful progress comes from focused work, deliberate practice, and precision in execution-maintaining a balanced perspective while continuously improving both the craft and the systems behind it.",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.85f)
        )
    }
}
