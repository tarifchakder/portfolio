package com.tarifchakder.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.materializekmp.DynamicTheme
import com.tarifchakder.presentation.widget.AnimateSkillText
import com.tarifchakder.presentation.widget.IconButtonRow
import com.tarifchakder.presentation.widget.ImageTitleSubtitleCard
import com.tarifchakder.theme.Typography
import com.tarifchakder.theme.seedColor
import com.tarifchakder.util.urlEncode
import org.jetbrains.compose.resources.painterResource

import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ic_dark
import portfolio.composeapp.generated.resources.ic_light
import portfolio.composeapp.generated.resources.rounded_pic

@Composable
fun App() {

    val currentMode = isSystemInDarkTheme()
    var isDarkMode = remember { mutableStateOf(currentMode) }

    DynamicTheme(
        seedColor = seedColor,
        isDarkTheme = isDarkMode.value,
        typography = Typography()
    ) {
        BoxWithConstraints(
            modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.surface)
        ) {
            val breakpoint = when {
                maxWidth < 550.dp -> WindowSizeClass.Compact
                maxWidth < 1000.dp -> WindowSizeClass.Medium
                else -> WindowSizeClass.Expanded
            }

            val horizontalPadding = when (breakpoint) {
                WindowSizeClass.Compact -> 12.dp
                WindowSizeClass.Medium -> 20.dp
                WindowSizeClass.Expanded -> 50.dp
            }
            val verticalPadding = when (breakpoint) {
                WindowSizeClass.Compact -> 8.dp
                WindowSizeClass.Medium -> 12.dp
                WindowSizeClass.Expanded -> 30.dp
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = horizontalPadding, vertical = verticalPadding)
                    .animateContentSize()
            ) {
                if (breakpoint == WindowSizeClass.Expanded) {
                    SideBar()
                    Spacer(modifier = Modifier.width(25.dp))
                }
                MainContent(
                    modifier = Modifier,
                    isDarkMode = isDarkMode
                )
            }
        }
    }
}

@Composable
private fun SideBar(
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current

    Card(
        modifier = modifier.fillMaxHeight().width(320.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.5.dp)
        ),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(Res.drawable.rounded_pic),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Md Tarif Chakder",
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
            AnimateSkillText(
                modifier = Modifier.padding(top = 10.dp)
            )
            HorizontalDivider(
                modifier = Modifier.padding(30.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant.copy(0.5f)
            )
            ImageTitleSubtitleCard(
                icon = Icons.Rounded.Email,
                title = "EMAIL",
                subtitle = "tarifchakdar@gmail.com",
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
                onClick = {
                    uriHandler.openUri("mailto:tarifchakdar@gmail.com")
                }
            )
            ImageTitleSubtitleCard(
                icon = Icons.Rounded.Phone,
                title = "MOBILE",
                subtitle = "+971 589513506",
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
                onClick = {
                    uriHandler.openUri("tel:+971589513506")
                }
            )
            ImageTitleSubtitleCard(
                icon = Icons.Rounded.LocationOn,
                title = "LOCATION",
                subtitle = "United Arab Emirates",
                modifier = Modifier.fillMaxWidth().padding(start = 10.dp, top = 5.dp, bottom = 5.dp),
                onClick = {
                    uriHandler.openUri("https://maps.app.goo.gl/dEgXiL8fcfqwRWgNA")
                }
            )
            Spacer(Modifier.height(10.dp))
            IconButtonRow {

            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    isDarkMode: MutableState<Boolean>
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(0.5.dp)
        ),
        border = BorderStroke(0.5.dp, MaterialTheme.colorScheme.outlineVariant.copy(0.5f))
    ) {
        Column {

        }


        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            AnimatedVisibility(visible = isDarkMode.value) {
                IconButton(
                    onClick = {
                        isDarkMode.value = !isDarkMode.value
                    },
                    modifier = Modifier.weight(.5f)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_light),
                        contentDescription = "ic_dark",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            if (!isDarkMode.value) {
                IconButton(
                    onClick = {
                        isDarkMode.value = !isDarkMode.value
                    },
                    modifier = Modifier.weight(.5f)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_dark),
                        contentDescription = "ic_dark",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            IconButton(
                onClick = {
                    //onHamburgerClick()
                },
                modifier = Modifier.weight(.5f)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_dark),
                    contentDescription = "ic_hamburger",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}