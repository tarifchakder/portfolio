package com.tarifchakder.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Design tokens for the glass ("liquid glass") language used across the portfolio.
 *
 * Two distinct materials exist and they are not interchangeable:
 *
 *  - **Chrome** - floating elements that sit above the scrolling page (nav bar, theme toggle).
 *    These get a real Haze blur of everything beneath them, so they need a heavier tint.
 *  - **Panels / cards** - surfaces that live inside the page. Only the top-level panels get a
 *    blur (of the aurora backdrop); the many small cards use a translucent gradient fill instead,
 *    which reads as glass over the aurora without paying for a blur pass per card.
 */
@Immutable
data class GlassTokens(
    val isDark: Boolean,
    // Backdrop
    val base: Color,
    val baseDeep: Color,
    val aurora: List<Color>,
    // Haze material
    val hazeBackground: Color,
    val panelTint: Color,
    val chromeTint: Color,
    /**
     * Used where blur is unavailable - notably Android 11 and below, which has no RenderEffect.
     * Much more opaque than the live tints, because there is no blurred backdrop to sit on.
     */
    val panelFallbackTint: Color,
    val chromeFallbackTint: Color,
    val panelBlur: Dp,
    val chromeBlur: Dp,
    val noiseFactor: Float,
    // Static glass
    val fill: List<Color>,
    val fillRaised: List<Color>,
    val strokeBright: Color,
    val strokeDim: Color,
    val sheen: Color,
    // Ink
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    // Accent
    val accent: Color,
    val accentAlt: Color,
    val accentSoft: Color,
    val shadow: Color,
) {
    /** Top-lit edge: bright where light would hit, fading to a dim bottom edge. */
    val strokeBrush: Brush
        get() = Brush.verticalGradient(listOf(strokeBright, strokeDim))

    val fillBrush: Brush get() = Brush.verticalGradient(fill)

    val fillRaisedBrush: Brush get() = Brush.verticalGradient(fillRaised)

    /**
     * Bright band along the very top of a surface - light catching the bevel. Concentrated in
     * the first ~10% of the height, which is what separates "wet glass" from a flat wash.
     */
    val glossBrush: Brush
        get() = Brush.verticalGradient(
            0.00f to sheen,
            0.09f to sheen.copy(alpha = sheen.alpha * 0.40f),
            0.36f to Color.Transparent,
            1.00f to Color.Transparent,
        )

    /** Broad diagonal wash under the gloss, giving the pane a direction of lighting. */
    val sheenBrush: Brush
        get() = Brush.linearGradient(
            0.00f to sheen.copy(alpha = sheen.alpha * 0.55f),
            0.42f to Color.Transparent,
        )

    val accentGradient: Brush get() = Brush.linearGradient(listOf(accent, accentAlt))
}

private val DarkGlass = GlassTokens(
    isDark = true,
    base = NightBase,
    baseDeep = NightDeep,
    aurora = listOf(NightAuroraIndigo, NightAuroraViolet, NightAuroraCyan, NightAuroraRose),
    hazeBackground = NightBase,
    panelTint = Color.White.copy(alpha = 0.05f),
    chromeTint = Color(0xFF0B1020).copy(alpha = 0.55f),
    panelFallbackTint = Color(0xFF171B30).copy(alpha = 0.82f),
    chromeFallbackTint = Color(0xFF111426).copy(alpha = 0.94f),
    panelBlur = 40.dp,
    chromeBlur = 30.dp,
    noiseFactor = 0.05f,
    fill = listOf(Color.White.copy(alpha = 0.09f), Color.White.copy(alpha = 0.035f)),
    fillRaised = listOf(Color.White.copy(alpha = 0.15f), Color.White.copy(alpha = 0.06f)),
    strokeBright = Color.White.copy(alpha = 0.26f),
    strokeDim = Color.White.copy(alpha = 0.05f),
    sheen = Color.White.copy(alpha = 0.17f),
    textPrimary = InkOnDark,
    textSecondary = InkOnDark.copy(alpha = 0.72f),
    textTertiary = InkOnDark.copy(alpha = 0.45f),
    accent = NightAccent,
    accentAlt = NightAccentAlt,
    accentSoft = NightAccent.copy(alpha = 0.16f),
    shadow = Color.Black.copy(alpha = 0.55f),
)

private val LightGlass = GlassTokens(
    isDark = false,
    base = DayBase,
    baseDeep = DayDeep,
    aurora = listOf(DayAuroraIndigo, DayAuroraViolet, DayAuroraCyan, DayAuroraRose),
    hazeBackground = DayBase,
    panelTint = Color.White.copy(alpha = 0.34f),
    chromeTint = Color.White.copy(alpha = 0.56f),
    panelFallbackTint = Color.White.copy(alpha = 0.78f),
    chromeFallbackTint = Color.White.copy(alpha = 0.92f),
    panelBlur = 40.dp,
    chromeBlur = 30.dp,
    noiseFactor = 0.03f,
    fill = listOf(Color.White.copy(alpha = 0.54f), Color.White.copy(alpha = 0.26f)),
    fillRaised = listOf(Color.White.copy(alpha = 0.78f), Color.White.copy(alpha = 0.44f)),
    strokeBright = Color.White.copy(alpha = 0.95f),
    // Ink rather than white: a white bottom edge on a near-white panel is invisible, and without
    // a darker lower edge the panels dissolve into the backdrop.
    strokeDim = InkOnLight.copy(alpha = 0.10f),
    sheen = Color.White.copy(alpha = 0.6f),
    textPrimary = InkOnLight,
    textSecondary = InkOnLight.copy(alpha = 0.7f),
    textTertiary = InkOnLight.copy(alpha = 0.45f),
    accent = DayAccent,
    accentAlt = DayAccentAlt,
    accentSoft = DayAccent.copy(alpha = 0.12f),
    shadow = Color(0xFF1E1B4B).copy(alpha = 0.24f),
)

val LocalGlass = staticCompositionLocalOf<GlassTokens> {
    error("GlassTokens not provided - wrap the UI in AppTheme { }")
}

/** Convenience accessor mirroring `MaterialTheme.colorScheme`. */
val glass: GlassTokens
    @Composable get() = LocalGlass.current

private val GlassShapes = Shapes(
    extraSmall = RoundedCornerShape(10.dp),
    small = RoundedCornerShape(14.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(26.dp),
    extraLarge = RoundedCornerShape(34.dp),
)

/**
 * Material is kept underneath the glass layer so that stock components (bottom sheets, progress
 * indicators, text defaults) stay correctly themed - but every surface colour is pushed to
 * transparent-ish values, because in this design surfaces are drawn by the glass components.
 */
private fun darkScheme(g: GlassTokens) = darkColorScheme(
    primary = g.accent,
    onPrimary = Color(0xFF0A0E20),
    primaryContainer = g.accent.copy(alpha = 0.24f),
    onPrimaryContainer = g.textPrimary,
    secondary = g.accentAlt,
    onSecondary = Color(0xFF04121A),
    background = g.base,
    onBackground = g.textPrimary,
    surface = g.base,
    onSurface = g.textPrimary,
    surfaceVariant = Color.White.copy(alpha = 0.08f),
    onSurfaceVariant = g.textSecondary,
    outline = Color.White.copy(alpha = 0.22f),
    outlineVariant = Color.White.copy(alpha = 0.12f),
    error = Color(0xFFFF7A8A),
)

private fun lightScheme(g: GlassTokens) = lightColorScheme(
    primary = g.accent,
    onPrimary = Color.White,
    primaryContainer = g.accent.copy(alpha = 0.16f),
    onPrimaryContainer = g.accent,
    secondary = g.accentAlt,
    onSecondary = Color.White,
    background = g.base,
    onBackground = g.textPrimary,
    surface = g.base,
    onSurface = g.textPrimary,
    surfaceVariant = Color.White.copy(alpha = 0.6f),
    onSurfaceVariant = g.textSecondary,
    outline = InkOnLight.copy(alpha = 0.22f),
    outlineVariant = InkOnLight.copy(alpha = 0.12f),
    error = Color(0xFFB3261E),
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val tokens = if (isDarkTheme) DarkGlass else LightGlass

    CompositionLocalProvider(LocalGlass provides tokens) {
        MaterialTheme(
            colorScheme = if (isDarkTheme) darkScheme(tokens) else lightScheme(tokens),
            typography = typography(),
            shapes = GlassShapes,
            content = content
        )
    }
}

// -- Shared surface modifiers --------------------------------------------------------------------

/** The hairline that separates one pane of glass from the next. */
fun Modifier.glassStroke(shape: Shape, tokens: GlassTokens, width: Dp = 1.dp): Modifier =
    border(width = width, brush = tokens.strokeBrush, shape = shape)

/** Translucent gradient body used by surfaces that do not carry their own blur pass. */
fun Modifier.glassFill(shape: Shape, tokens: GlassTokens, raised: Boolean = false): Modifier =
    background(brush = if (raised) tokens.fillRaisedBrush else tokens.fillBrush, shape = shape)
