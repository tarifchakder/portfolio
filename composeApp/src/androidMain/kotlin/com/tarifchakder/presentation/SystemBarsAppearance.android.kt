package com.tarifchakder.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

@Composable
actual fun SystemBarsAppearance(useDarkIcons: Boolean) {
    val view = LocalView.current
    if (view.isInEditMode) return

    val activity = view.context as? android.app.Activity ?: return

    SideEffect {
        val window = activity.window
        val controller = WindowCompat.getInsetsController(window, view)
        controller.isAppearanceLightStatusBars = useDarkIcons
        controller.isAppearanceLightNavigationBars = useDarkIcons
    }
}
