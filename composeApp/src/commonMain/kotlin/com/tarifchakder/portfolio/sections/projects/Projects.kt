package com.tarifchakder.portfolio.sections.projects

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.portfolio.sections.projects.components.ProjectCard
import com.tarifchakder.portfolio.domain.WindowSizeClass
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ai_chat_keyboard_icon
import portfolio.composeapp.generated.resources.audio_amplifier_icon
import portfolio.composeapp.generated.resources.bt_remote_app_icon
import portfolio.composeapp.generated.resources.ppt_file_viewer_icon
import portfolio.composeapp.generated.resources.vin_icon

@Composable
fun Projects() {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val breakpoint = when {
            maxWidth < 890.dp -> WindowSizeClass.Compact
            maxWidth < 1440.dp -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }

        Box(
            modifier = Modifier.fillMaxWidth().background(Color.Black),
            contentAlignment = Alignment.Center
        )
        {

            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = if (breakpoint == WindowSizeClass.Expanded) 25.dp else if (breakpoint == WindowSizeClass.Medium) 15.dp else 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Text("Projects", color = Color.White, fontSize = 15.sp)


                when (breakpoint) {

                    WindowSizeClass.Compact -> {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                modifier = Modifier.weight(1f),
                                fontSizeFeatures = 8.sp,
                                breakPoint = breakpoint,
                                onPlayStore = true
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                modifier = Modifier.weight(1f),
                                fontSizeFeatures = 8.sp,
                                breakPoint = breakpoint,
                                appName = "AI Chat Keyboard",
                                projectDate = "Dec 2024 - Dec 2024",
                                appImage = Res.drawable.ai_chat_keyboard_icon,
                                projectKeyFeatures = listOf(
                                    "Multi-functional AI Chat Keyboard",
                                    "Customizable keyboard themes",
                                    "Intelligent text correction using Gemini",
                                    "AI-based word suggestions for typing",
                                    "Portrait & landscape mode compatibility"
                                )
                            )
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                modifier = Modifier.weight(1f),
                                fontSizeFeatures = 8.sp,
                                breakPoint = breakpoint,
                                appName = "BT Tv Remote",
                                projectDate = "Feb 2025 - Mar 2024",
                                appImage = Res.drawable.bt_remote_app_icon,
                                projectKeyFeatures = listOf(
                                    "Bluetooth remote control for TVs",
                                    "Gamepad mode for gaming",
                                    "Google Cast for media streaming",
                                    "Channel & volume control",
                                    "Multi-language support"
                                )
                            )
                        }

                    }

                    WindowSizeClass.Medium -> {

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                breakPoint = breakpoint,
                                modifier = Modifier.weight(1f),
                                onPlayStore = true
                            )
                            ProjectCard(
                                breakPoint = breakpoint, modifier = Modifier.weight(1f),
                                appName = "AI Chat Keyboard",
                                projectDate = "Dec 2024 - Dec 2024",
                                appImage = Res.drawable.ai_chat_keyboard_icon,
                                projectKeyFeatures = listOf(
                                    "Multi-functional AI Chat Keyboard",
                                    "Customizable keyboard themes",
                                    "Intelligent text correction using Gemini",
                                    "AI-based word suggestions for typing",
                                    "Portrait & landscape mode compatibility"
                                )
                            )

                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                breakPoint = breakpoint, modifier = Modifier.weight(1f),
                                appName = "BT Tv Remote",
                                projectDate = "Feb 2025 - Mar 2024",
                                appImage = Res.drawable.bt_remote_app_icon,
                                projectKeyFeatures = listOf(
                                    "Bluetooth remote control for TVs",
                                    "Gamepad mode for gaming",
                                    "Google Cast for media streaming",
                                    "Channel & volume control",
                                    "Multi-language support"
                                )
                            )
                            ProjectCard(
                                breakPoint = breakpoint, modifier = Modifier.weight(1f),
                                appName = "Audio Amplifier",
                                appImage = Res.drawable.audio_amplifier_icon,
                                projectDate = "Jul 2024 - Jul 2024",
                                projectKeyFeatures = listOf(
                                    "Audio amplification & volume boosting",
                                    "Advanced equalizer for custom tuning",
                                    "Speaker cleaner for improved audio",
                                    "Seamless local audio & video playback",
                                    "Intuitive interface for effortless control"
                                ),
                                onPlayStore = true,
                                appDownloads = "",
                                appStoreLink = "https://play.google.com/store/apps/details?id=com.strco.musicplayer.audioamplifier.volumebooster.speakercleaner"
                            )

                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                breakPoint = breakpoint, modifier = Modifier.weight(1f),
                                appName = "PPT File Viewer",
                                projectDate = "Sep 2024 - Sep 2024",
                                appImage = Res.drawable.ppt_file_viewer_icon,
                                projectKeyFeatures = listOf(
                                    "Multi-format document viewer",
                                    "Supports PPT, DOC, XLS, PDF & CSV",
                                    "Secure storage access",
                                    "Elegant & user-friendly interface",
                                    "Fast & efficient document rendering"
                                )
                            )
                            ProjectCard(
                                breakPoint = breakpoint, modifier = Modifier.weight(1f),
                                appImage = Res.drawable.vin_icon, appName = "VIN Decoder",
                                projectDate = "Oct 2024 - Oct 2024",
                                projectKeyFeatures = listOf(
                                    "Instant VIN lookup for vehicle details",
                                    "Real-time data on make, model & more",
                                    "Multi-language support for global users",
                                    "Ideal for buyers, sellers & enthusiasts",
                                    "Secure & reliable vehicle data retrieval"
                                )
                            )

                        }

                    }

                    WindowSizeClass.Expanded -> {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                breakPoint = breakpoint,
                                modifier = Modifier.weight(1f),
                                onPlayStore = true
                            )
                            ProjectCard(
                                breakPoint = breakpoint,
                                modifier = Modifier.weight(1f),
                                appName = "AI Chat Keyboard",
                                projectDate = "Dec 2024 - Dec 2024",
                                appImage = Res.drawable.ai_chat_keyboard_icon,
                                projectKeyFeatures = listOf(
                                    "Multi-functional AI Chat Keyboard",
                                    "Customizable keyboard themes",
                                    "Intelligent text correction using Gemini",
                                    "AI-based word suggestions for typing",
                                    "Portrait & landscape mode compatibility"
                                )
                            )
                            ProjectCard(
                                breakPoint = breakpoint,
                                modifier = Modifier.weight(1f),
                                appName = "BT Tv Remote",
                                projectDate = "Feb 2025 - Mar 2024",
                                appImage = Res.drawable.bt_remote_app_icon,
                                projectKeyFeatures = listOf(
                                    "Bluetooth remote control for TVs",
                                    "Gamepad mode for gaming",
                                    "Google Cast for media streaming",
                                    "Channel & volume control",
                                    "Multi-language support"
                                )
                            )
                        }

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ProjectCard(
                                breakPoint = breakpoint,
                                modifier = Modifier.weight(1f),
                                appName = "Audio Amplifier",
                                appImage = Res.drawable.audio_amplifier_icon,
                                projectDate = "Jul 2024 - Jul 2024",
                                projectKeyFeatures = listOf(
                                    "Audio amplification & volume boosting",
                                    "Advanced equalizer for custom tuning",
                                    "Speaker cleaner for improved audio",
                                    "Seamless local audio & video playback",
                                    "Intuitive interface for effortless control"
                                ),
                                onPlayStore = true,
                                appDownloads = "",
                                appStoreLink = "https://play.google.com/store/apps/details?id=com.strco.musicplayer.audioamplifier.volumebooster.speakercleaner"
                            )
                            ProjectCard(
                                breakPoint = breakpoint, modifier = Modifier.weight(1f),
                                appName = "PPT File Viewer",
                                projectDate = "Sep 2024 - Sep 2024",
                                appImage = Res.drawable.ppt_file_viewer_icon,
                                projectKeyFeatures = listOf(
                                    "Multi-format document viewer",
                                    "Supports PPT, DOC, XLS, PDF & CSV",
                                    "Secure storage access",
                                    "Elegant & user-friendly interface",
                                    "Fast & efficient document rendering"
                                )
                            )
                            ProjectCard(
                                breakPoint = breakpoint, modifier = Modifier.weight(1f),
                                appImage = Res.drawable.vin_icon, appName = "VIN Decoder",
                                projectDate = "Oct 2024 - Oct 2024",
                                projectKeyFeatures = listOf(
                                    "Instant VIN lookup for vehicle details",
                                    "Real-time data on make, model & more",
                                    "Multi-language support for global users",
                                    "Ideal for buyers, sellers & enthusiasts",
                                    "Secure & reliable vehicle data retrieval"
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }

    }

}