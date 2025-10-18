@file:OptIn(InternalResourceApi::class)

package portfolio.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/portfolio.composeapp.generated.resources/"

internal val Res.drawable.compose_multiplatform: DrawableResource by lazy {
      DrawableResource("drawable:compose_multiplatform", setOf(
        ResourceItem(setOf(), "${MD}drawable/compose-multiplatform.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_dark: DrawableResource by lazy {
      DrawableResource("drawable:ic_dark", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_dark.xml", -1, -1),
      ))
    }

internal val Res.drawable.ic_light: DrawableResource by lazy {
      DrawableResource("drawable:ic_light", setOf(
        ResourceItem(setOf(), "${MD}drawable/ic_light.xml", -1, -1),
      ))
    }

internal val Res.drawable.rounded_pic: DrawableResource by lazy {
      DrawableResource("drawable:rounded_pic", setOf(
        ResourceItem(setOf(), "${MD}drawable/rounded_pic.png", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectCommonMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("compose_multiplatform", Res.drawable.compose_multiplatform)
  map.put("ic_dark", Res.drawable.ic_dark)
  map.put("ic_light", Res.drawable.ic_light)
  map.put("rounded_pic", Res.drawable.rounded_pic)
}
