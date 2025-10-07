package com.tarifchakder.portfolio.sections.header


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.portfolio.domain.WindowSizeClass
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ic_dark
import portfolio.composeapp.generated.resources.ic_hamburger
import portfolio.composeapp.generated.resources.ic_light

@Composable
fun TopHeader(
    onAboutClick: () -> Unit,
    onHomeClick: () -> Unit = {},
    onExperienceClick: () -> Unit = {},
    onProjectClick: () -> Unit = {},
    onContactClick: () -> Unit = {},
    onHamburgerClick: () -> Unit = {}
    ) {

    var isDarkMode by remember { mutableStateOf(false) }


    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {

        val breakpoint = when {
            maxWidth < 600.dp -> WindowSizeClass.Compact
            maxWidth < 840.dp -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }

        when (breakpoint) {
            WindowSizeClass.Compact -> {
                Row(
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                        .background(Color(0xFFF5F5F5).copy(alpha = .7f))
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "M Aban", fontSize = 16.sp, modifier = Modifier.weight(2f))

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        AnimatedVisibility(visible = isDarkMode) {
                            IconButton(
                                onClick = {
                                    isDarkMode = !isDarkMode
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
                        if (!isDarkMode) {
                            IconButton(
                                onClick = {
                                    isDarkMode = !isDarkMode
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
                                onHamburgerClick()
                            },
                            modifier = Modifier.weight(.5f)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_hamburger),
                                contentDescription = "ic_hamburger",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }


                }
            }
            WindowSizeClass.Medium -> {
                Row(
                    modifier = Modifier.fillMaxWidth().height(30.dp)
                        .background(Color(0xFFF5F5F5).copy(alpha = .7f))
                        .padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "M Aban", fontSize = 16.sp, modifier = Modifier.weight(3f))

                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.End
                    ) {
                        AnimatedVisibility(visible = isDarkMode) {
                            IconButton(
                                onClick = {
                                    isDarkMode = !isDarkMode
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
                        if (!isDarkMode) {
                            IconButton(
                                onClick = {
                                    isDarkMode = !isDarkMode
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
                                onHamburgerClick()
                            },
                            modifier = Modifier.weight(.5f)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_hamburger),
                                contentDescription = "ic_hamburger",
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }


                }
            }
            else -> {
                Row(
                    modifier = Modifier.fillMaxWidth().height(100.dp)
                        .background(Color(0xFFF5F5F5).copy(alpha = .7f))
                        .padding(horizontal = 18.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = "M Aban", fontSize = 12.sp, modifier = Modifier.weight(.5f))

                    Row(
                        modifier = Modifier.weight(2f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {

                        HeaderButton(modifier = Modifier, text = "Home", onHomeClick)
                        HeaderButton(modifier = Modifier, text = "About", onAboutClick)
                        HeaderButton(modifier = Modifier, text = "Experience", onExperienceClick)
                        HeaderButton(modifier = Modifier, text = "Projects", onProjectClick)
                        HeaderButton(modifier = Modifier, text = "Contact", onContactClick)

                    }

                    IconButton(
                        onClick = {
                            isDarkMode = !isDarkMode
                        },
                        modifier = Modifier.weight(.5f)
                    ) {
                        AnimatedVisibility(visible = isDarkMode) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_light),
                                contentDescription = "ic_dark",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                        if (!isDarkMode) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_dark),
                                contentDescription = "ic_dark",
                                modifier = Modifier.padding(5.dp)
                            )
                        }
                    }

                }
            }
        }


    }

}