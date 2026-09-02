package com.tarifchakder.presentation.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.tarifchakder.theme.GlassTokens
import com.tarifchakder.theme.LocalGlass
import com.tarifchakder.theme.glassFill
import com.tarifchakder.theme.glassStroke
import com.tarifchakder.util.noRippleClickable
import dev.chrisbanes.haze.ExperimentalHazeApi
import dev.chrisbanes.haze.HazeInputScale
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

/**
 * Haze source covering the aurora backdrop only. Panels sample this, so they frost the colour
 * field without recursively sampling themselves.
 */
val LocalBackdropHaze = staticCompositionLocalOf<HazeState> {
    error("Backdrop HazeState not provided")
}

/**
 * Haze source covering the aurora *and* the whole scrolling page. Only floating chrome drawn
 * above the page (nav bar, theme toggle) samples this, which is what makes content visibly
 * smear as it passes underneath.
 */
val LocalPageHaze = staticCompositionLocalOf<HazeState> {
    error("Page HazeState not provided")
}

/**
 * Specular highlight: a bright band along the top bevel plus a broad diagonal wash.
 *
 * Both are drawn *before* [drawContent] so the highlight sits between the surface body and the
 * text on it - painting it over the content would wash out labels at the top of every card.
 */
fun Modifier.sheen(shape: Shape, tokens: GlassTokens): Modifier = this
    .clip(shape)
    .drawWithContent {
        drawRect(brush = tokens.sheenBrush)
        drawRect(brush = tokens.glossBrush)
        drawContent()
    }

/**
 * Soft, colour-matched drop shadow.
 *
 * Deliberately `dropShadow` and never `Modifier.shadow`. `Modifier.shadow` renders an
 * *elevation* shadow, which needs an opaque occluder and introduces a graphicsLayer. On glass
 * (which is translucent by definition) that produced two visible defects:
 *
 *  - a lighter rectangle bleeding through the middle of every card, and
 *  - a seam on blurred panels, because the extra graphicsLayer made Haze sample the wrong region.
 *
 * `dropShadow` paints a plain blurred silhouette behind the content with no layer and no light
 * source, so it composites correctly under a translucent surface.
 */
fun Modifier.glassShadow(shape: Shape, tokens: GlassTokens, elevation: Dp = 18.dp): Modifier =
    dropShadow(shape) {
        radius = elevation.toPx()
        offset = Offset(0f, (elevation * 0.35f).toPx())
        color = tokens.shadow
    }

/** Shared body for the two blurred materials. */
@OptIn(ExperimentalHazeApi::class)
@Composable
private fun BlurredGlassSurface(
    modifier: Modifier,
    shape: Shape,
    elevation: Dp,
    hazeState: HazeState,
    blur: Dp,
    tint: Color,
    fallback: Color,
    content: @Composable BoxScope.() -> Unit
) {
    val g = LocalGlass.current

    Box(
        modifier = modifier
            .glassShadow(shape, g, elevation)
            .clip(shape)
            .hazeEffect(state = hazeState) {
                blurRadius = blur
                noiseFactor = g.noiseFactor
                backgroundColor = g.hazeBackground
                tints = listOf(HazeTint(tint))
                fallbackTint = HazeTint(fallback)
                // Must stay None: downscaling the blur input (Auto/Fixed) renders the result back
                // a few pixels short of the node, leaving a hard seam inside the rounded corners.
                inputScale = HazeInputScale.None
            }
            .glassStroke(shape, g)
            .sheen(shape, g),
        content = content
    )
}

/**
 * A top-level pane of glass: real blur of the aurora, plus tint, hairline stroke and sheen.
 *
 * Reserved for the handful of large surfaces (profile rail, section panels). Small repeated
 * surfaces should use [GlassCard], which costs no blur pass.
 */
@Composable
fun GlassPanel(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraLarge,
    elevation: Dp = 20.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val g = LocalGlass.current

    BlurredGlassSurface(
        modifier = modifier,
        shape = shape,
        elevation = elevation,
        hazeState = LocalBackdropHaze.current,
        blur = g.panelBlur,
        tint = g.panelTint,
        fallback = g.panelFallbackTint,
        content = content
    )
}

/**
 * Floating chrome material. Samples the full page, so whatever scrolls beneath it is blurred.
 * Tinted more heavily than [GlassPanel] to keep its own contents legible over arbitrary content.
 */
@Composable
fun GlassChrome(
    modifier: Modifier = Modifier,
    shape: Shape = CircleShape,
    elevation: Dp = 24.dp,
    content: @Composable BoxScope.() -> Unit
) {
    val g = LocalGlass.current

    BlurredGlassSurface(
        modifier = modifier,
        shape = shape,
        elevation = elevation,
        hazeState = LocalPageHaze.current,
        blur = g.chromeBlur,
        tint = g.chromeTint,
        fallback = g.chromeFallbackTint,
        content = content
    )
}

/**
 * Lightweight glass: a translucent gradient body rather than a blur pass. Optionally interactive,
 * in which case it lifts and brightens on hover.
 *
 * **Carries no drop shadow, by design.** A card rests *on* a [GlassPanel] rather than floating
 * above the page, so it is read by its fill and its lit edge. It also cannot have one: the fill is
 * translucent, so any shadow drawn behind shows straight through the body and makes the card
 * render *darker* than the panel underneath it - the opposite of a raised surface. Panels and
 * chrome can carry shadows only because Haze paints an opaque backdrop that occludes them.
 */
@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.large,
    raised: Boolean = false,
    onClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val g = LocalGlass.current
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val interactive = onClick != null

    val lift by animateFloatAsState(
        targetValue = if (interactive && hovered) 1.02f else 1f,
        animationSpec = tween(220),
        label = "cardLift"
    )

    Box(
        modifier = modifier
            .graphicsLayer(scaleX = lift, scaleY = lift)
            .glassFill(shape, g, raised = raised || (interactive && hovered))
            .glassStroke(shape, g)
            .sheen(shape, g)
            .then(
                if (interactive) {
                    Modifier
                        .hoverable(interactionSource)
                        .pointerHoverIcon(PointerIcon.Hand)
                        .noRippleClickable(interactionSource) { onClick?.invoke() }
                } else {
                    Modifier
                }
            ),
        content = content
    )
}

/** Small circular glass control - social links, toggles, icon affordances. */
@Composable
fun GlassIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 44.dp,
    shape: Shape = CircleShape,
    content: @Composable BoxScope.() -> Unit
) {
    val g = LocalGlass.current
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    val scale by animateFloatAsState(
        targetValue = if (hovered) 1.12f else 1f,
        animationSpec = tween(200),
        label = "iconButtonScale"
    )

    Box(
        modifier = modifier
            .size(size)
            .graphicsLayer(scaleX = scale, scaleY = scale)
            .glassFill(shape, g, raised = hovered)
            .glassStroke(shape, g)
            .sheen(shape, g)
            .hoverable(interactionSource)
            .pointerHoverIcon(PointerIcon.Hand)
            .noRippleClickable(interactionSource, onClick = onClick),
        contentAlignment = Alignment.Center,
        content = content
    )
}

/** Pill label. [accent] switches it from neutral glass to a tinted brand chip. */
@Composable
fun GlassChip(
    text: String,
    modifier: Modifier = Modifier,
    accent: Boolean = false,
) {
    val g = LocalGlass.current
    val shape = RoundedCornerShape(999.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .then(
                if (accent) {
                    Modifier.background(g.accentSoft, shape)
                } else {
                    Modifier.glassFill(shape, g)
                }
            )
            .glassStroke(shape, g)
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            color = if (accent) g.accent else g.textSecondary,
            textAlign = TextAlign.Center
        )
    }
}

/** Short accent rule used under section titles. */
@Composable
fun AccentRule(modifier: Modifier = Modifier, width: Dp = 46.dp, height: Dp = 4.dp) {
    val g = LocalGlass.current
    Box(
        modifier = modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(999.dp))
            .background(g.accentGradient)
    )
}
