package com.tarifchakder.portfolio.sections.contact.components

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import com.tarifchakder.portfolio.utils.Utils.openUrl
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.github



@Composable
fun IconButton(
    image: DrawableResource = Res.drawable.github,
    url: String
){

    IconButton(
        onClick = {
            openUrl(url)
        },
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = Color(0xFF6F6F71)
        ),
        modifier = Modifier.pointerHoverIcon(PointerIcon.Hand)
    ) {
        Icon(painter = painterResource(image), contentDescription = "")
    }

}