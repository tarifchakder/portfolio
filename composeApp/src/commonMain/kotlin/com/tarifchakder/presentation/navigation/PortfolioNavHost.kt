package com.tarifchakder.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.tarifchakder.domain.NavDestination
import com.tarifchakder.domain.WindowSizeClass
import com.tarifchakder.presentation.screen.AboutScreen
import com.tarifchakder.presentation.screen.ResumeScreen
import com.tarifchakder.presentation.screen.WorkScreen

@Composable
fun PortfolioNavHost(
    destination: NavDestination,
    breakpoint: WindowSizeClass
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Top
    ) {
        when (destination) {
            NavDestination.Home -> AboutScreen(breakpoint = breakpoint)
            NavDestination.Resume -> ResumeScreen(breakpoint = breakpoint)
            NavDestination.WORK -> WorkScreen()
        }
    }
}
