package com.tarifchakder.domain

import org.jetbrains.compose.resources.StringResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.nav_about
import portfolio.composeapp.generated.resources.nav_resume
import portfolio.composeapp.generated.resources.nav_work

enum class NavDestination(val labelRes: StringResource) {
    Home(Res.string.nav_about),
    Resume(Res.string.nav_resume),
    WORK(Res.string.nav_work),
}