package com.tarifchakder.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.materializekmp.DynamicTheme
import com.tarifchakder.presentation.SystemBarsAppearance
import com.tarifchakder.theme.seedColor
import com.tarifchakder.theme.typography

private const val ContactEmail = "tarifchakdar@gmail.com"

@Composable
fun ZPlayerTermsApp() {
    val isDarkTheme = isSystemInDarkTheme()
    SystemBarsAppearance(useDarkIcons = !isDarkTheme)

    DynamicTheme(
        seedColor = seedColor,
        isDarkTheme = isDarkTheme,
        typography = typography()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLowest)
                .padding(horizontal = 16.dp, vertical = 24.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 860.dp),
                shape = RoundedCornerShape(24.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 1.dp,
                shadowElevation = 4.dp
            ) {
                TermsContent()
            }
        }
    }
}

@Composable
private fun TermsContent() {
    val uriHandler = LocalUriHandler.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 32.dp)
    ) {
        Text(
            text = "ZPlayer Terms and Conditions",
            style = MaterialTheme.typography.headlineMedium.copy(
                fontSize = 26.sp,
                lineHeight = 32.sp
            ),
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Last updated: August 7, 2026",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(24.dp))
        BodyText("Please read these Terms and Conditions (\"Terms\") before using ZPlayer (\"the App\"). By installing, opening, or using the App, you agree to these Terms. If you do not agree, please do not use the App.")

        TermsSection("1. About ZPlayer") {
            BodyText("ZPlayer is a media player and local media utility app. Depending on the version and device support, the App may let you:")
            BulletList(
                "play videos and audio files stored on your device;",
                "browse local media and folders;",
                "manage selected files;",
                "convert supported videos to audio files;",
                "hide selected media files in a private area;",
                "share selected media or converted files using Android sharing features;",
                "receive playback or conversion notifications; and",
                "view advertisements."
            )
        }

        TermsSection("2. Permissions") {
            BodyText("The App may request permissions that are required for its core features. These may include access to audio, video, images, notifications, foreground service playback, network access, advertising ID, and file access features.")
            BodyText("You are responsible for choosing whether to grant permissions. If you deny or remove a permission, some or all features of the App may not work until the permission is granted again.")
            BodyText("The App should request only permissions that are needed for visible app features. Permissions must not be used for hidden, unrelated, or misleading purposes.")
        }

        TermsSection("3. Your Content") {
            BodyText("Your media files, folders, filenames, metadata, converted files, playlists, and hidden files remain your responsibility. The App does not give you ownership of content that you do not already have the right to use.")
            BodyText("You agree not to use the App to copy, convert, store, hide, share, or distribute content in a way that violates copyright law, privacy rights, or any applicable law.")
        }

        TermsSection("4. Local Media, File Management, and Hidden Files") {
            BodyText("The App may read local media and file information to show your music, videos, images, folders, file details, thumbnails, playback progress, conversion status, and related app features.")
            BodyText("If you use file management, delete, hide, restore, conversion, or sharing features, you are responsible for checking the selected files and confirming that you want the action to happen. Some actions may move, change, create, share, or delete files on your device.")
            BodyText("The hidden files feature is intended to provide app-level privacy on your device. It is not a guarantee against all device access, data recovery, operating system access, backups, malware, rooted devices, or physical access to your device.")
        }

        TermsSection("5. Video to Audio Conversion") {
            BodyText("The conversion feature is provided for files that you own or have permission to convert. Conversion quality, speed, output compatibility, and success may vary by device, file type, codec, file size, and storage availability.")
        }

        TermsSection("6. Playback and Notifications") {
            BodyText("The App may use foreground services and notifications to support media playback, playback controls, and conversion progress. If notification permission is denied, some notification-based controls or updates may be unavailable.")
        }

        TermsSection("7. Advertisements and Third-Party Services") {
            BodyText("The App may show advertisements through Google AdMob or another advertising provider. Third-party advertising services may collect or process data according to their own policies and applicable law.")
            BodyText("The App may also use Android system features or third-party services for sharing, opening links, media playback support, or advertisements. ZPlayer is not responsible for the content, availability, or practices of third-party services.")
        }

        TermsSection("8. Privacy") {
            BodyText("These Terms are not a Privacy Policy. ZPlayer should also provide a separate Privacy Policy that explains what data the App accesses, collects, uses, shares, stores, and deletes, including data handled by third-party SDKs such as ads.")
            BodyText("If the App is distributed on Google Play, the Privacy Policy and Play Console Data safety section should accurately match the App's behavior.")
        }

        TermsSection("9. User Responsibilities") {
            BodyText("You agree to use the App lawfully and responsibly. You agree not to:")
            BulletList(
                "use the App to violate another person's rights;",
                "use the App to distribute illegal, harmful, abusive, or infringing content;",
                "attempt to bypass device security or app restrictions;",
                "misuse file access, hidden files, conversion, sharing, or playback features;",
                "interfere with advertisements, services, or app functionality; or",
                "reverse engineer, modify, or redistribute the App unless allowed by law."
            )
        }

        TermsSection("10. Availability and Changes") {
            BodyText("The App may change over time. Features may be added, changed, limited, removed, or temporarily unavailable. The App may not work on all devices, Android versions, files, codecs, or storage configurations.")
        }

        TermsSection("11. No Warranty") {
            BodyText("The App is provided \"as is\" and \"as available\" to the fullest extent allowed by law. ZPlayer does not guarantee that the App will be error-free, uninterrupted, secure, compatible with every file, or suitable for every purpose.")
        }

        TermsSection("12. Limitation of Liability") {
            BodyText("To the fullest extent allowed by law, ZPlayer and its developer will not be liable for indirect, incidental, special, consequential, or punitive damages, or for loss of data, files, content, revenue, profits, or device functionality arising from your use of the App.")
            BodyText("Some jurisdictions do not allow certain limitations of liability, so some parts of this section may not apply to you.")
        }

        TermsSection("13. Termination") {
            BodyText("You may stop using the App at any time. ZPlayer may restrict or stop access to the App or any feature if required by law, policy, security, technical reasons, or violation of these Terms.")
        }

        TermsSection("14. Governing Law") {
            BodyText("These Terms are governed by applicable law. Any rights you have under mandatory laws that apply to you remain unaffected.")
        }

        TermsSection("15. Contact") {
            BodyText("Developer/Publisher: Md Tarif Chakder")
            Text(
                text = ContactEmail,
                modifier = Modifier.clickable { uriHandler.openUri("mailto:$ContactEmail") },
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 14.sp,
                    lineHeight = 22.sp
                ),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(Modifier.height(28.dp))
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(Modifier.height(20.dp))
        Text(
            text = "© 2026 Md Tarif Chakder. All rights reserved.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(Modifier.height(8.dp))
    }
}

@Composable
private fun TermsSection(title: String, content: @Composable () -> Unit) {
    Spacer(Modifier.height(24.dp))
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 17.sp,
            lineHeight = 23.sp
        ),
        color = MaterialTheme.colorScheme.onSurface
    )
    Spacer(Modifier.height(10.dp))
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        content()
    }
}

@Composable
private fun BodyText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge.copy(
            fontSize = 14.sp,
            lineHeight = 22.sp,
            letterSpacing = 0.15.sp
        ),
        color = MaterialTheme.colorScheme.onSurfaceVariant
    )
}

@Composable
private fun BulletList(vararg items: String) {
    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        items.forEach { item ->
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "•",
                    modifier = Modifier.width(22.dp),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        lineHeight = 22.sp
                    ),
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = item,
                    modifier = Modifier.weight(1f),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp,
                        lineHeight = 22.sp,
                        letterSpacing = 0.15.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
