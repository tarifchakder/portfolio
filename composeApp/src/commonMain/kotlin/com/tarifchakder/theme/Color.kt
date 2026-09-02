package com.tarifchakder.theme

import androidx.compose.ui.graphics.Color

/**
 * Raw palette. Nothing here is used directly by UI code - it is consumed by [GlassTokens]
 * and the Material colour schemes in `GlassTheme.kt`, which is where semantic meaning is attached.
 *
 * The aurora hues are deliberately saturated: glassmorphism only reads as glass when there is
 * something colourful behind the panel for the blur to pick up.
 */

// -- Dark ("midnight") ---------------------------------------------------------------------------

val NightBase = Color(0xFF07091A)
val NightDeep = Color(0xFF03040D)

val NightAuroraIndigo = Color(0xFF4F46E5)
val NightAuroraViolet = Color(0xFF9333EA)
val NightAuroraCyan = Color(0xFF06B6D4)
val NightAuroraRose = Color(0xFFE11D48)

val NightAccent = Color(0xFF8B93FF)
val NightAccentAlt = Color(0xFF34D8F0)

// -- Light ("porcelain") -------------------------------------------------------------------------

// Deliberately deeper than a "white page": glass panels are themselves near-white, so the
// backdrop has to carry real colour or the panel edges dissolve into it.
val DayBase = Color(0xFFDFE6F6)
val DayDeep = Color(0xFFC5D0EA)

val DayAuroraIndigo = Color(0xFF5B5FE9)
val DayAuroraViolet = Color(0xFF9D4EF0)
val DayAuroraCyan = Color(0xFF14C3E0)
val DayAuroraRose = Color(0xFFF95F86)

val DayAccent = Color(0xFF4F46E5)
val DayAccentAlt = Color(0xFF0891B2)

// -- Ink -----------------------------------------------------------------------------------------

val InkOnDark = Color(0xFFF2F4FF)
val InkOnLight = Color(0xFF10142B)
