package com.tarifchakder.portfolio.sections.experience


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.portfolio.sections.experience.components.CompanySection
import com.tarifchakder.portfolio.utils.Utils.openUrl
import com.tarifchakder.portfolio.domain.WindowSizeClass
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.mobologics_logo

@Composable
fun Experience() {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val breakpoint = when {
            maxWidth < 450.dp -> WindowSizeClass.Compact
            maxWidth < 840.dp -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }


        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text("Experience", fontSize = 15.sp, fontWeight = FontWeight.Bold)

            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CompanySection(
                    image = Res.drawable.mobologics_logo,
                    companyName = "Mobologics",
                    role = "Junior Android Developer",
                    timePeriod = "Dec 2024 - Present",
                    breakPoint = breakpoint,
                    onClick = {
                        openUrl("https://www.mobologics.com/")
                    }
                )
                CompanySection(
                    breakPoint = breakpoint,
                    onClick = {
                        openUrl("https://microwebsol.com/")
                    }
                )

            }

        }

    }

}