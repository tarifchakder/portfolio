package com.tarifchakder.presentation

import androidx.compose.runtime.Composable

/**
 * Keeps status/navigation bar icon contrast (light vs dark) in sync with the app's own
 * theme toggle, rather than just the OS default (which can disagree with an in-app override).
 * No-op on platforms without a system status/navigation bar (desktop, web, iOS).
 */
@Composable
expect fun SystemBarsAppearance(useDarkIcons: Boolean)
