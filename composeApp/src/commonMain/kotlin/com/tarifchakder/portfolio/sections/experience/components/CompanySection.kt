package com.tarifchakder.portfolio.sections.experience.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.portfolio.domain.WindowSizeClass
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.micro_web_logo

@Composable
fun CompanySection(
    image: DrawableResource = Res.drawable.micro_web_logo,
    companyName: String = "MicroWeb Solutions Pvt. Ltd",
    role: String = "Android Developer Internship",
    timePeriod: String = "Jun 2024 - Nov 2024",
    breakPoint: WindowSizeClass,
    onClick: () -> Unit = {}
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        ElevatedCard(
            onClick = {
                onClick()

            },
           modifier =  Modifier.pointerHoverIcon(PointerIcon.Hand)
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = companyName,
                modifier = Modifier.padding(8.dp).size(if (breakPoint == WindowSizeClass.Compact) 55.dp else 36.dp).align(Alignment.CenterHorizontally)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(if (breakPoint == WindowSizeClass.Compact)0.dp else 2.dp)
        ) {
            Text(companyName, fontSize = 9.sp, fontWeight = FontWeight.Medium)
            Text(role, fontSize = 6.sp, color = Color.Gray)
            Text(timePeriod, fontSize = 6.sp, color = Color.Gray)
        }


    }

}