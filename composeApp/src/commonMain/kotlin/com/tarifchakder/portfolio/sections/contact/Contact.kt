package com.tarifchakder.portfolio.sections.contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tarifchakder.portfolio.sections.contact.components.IconButton
import portfolio.composeapp.generated.resources.Res
import portfolio.composeapp.generated.resources.ic_email
import portfolio.composeapp.generated.resources.ic_instagram
import portfolio.composeapp.generated.resources.ic_linkedin

@Composable
fun Contact() {

    Box(
        modifier = Modifier.fillMaxWidth().background(Color(0xFF303033)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Contact", color = Color(0xFF6F6F71), fontSize = 12.sp)
            Row(
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(url = "https://github.com/Aban3049")
                IconButton(image = Res.drawable.ic_instagram, url = "https://www.instagram.com/ch_m_aban/")
                IconButton(image = Res.drawable.ic_linkedin, url = "https://www.linkedin.com/in/aban-android-developer/")
                IconButton(image = Res.drawable.ic_email, url = "mailto:muhammadabandev@gmail.com.com")
            }
            Text(
                "2024 © AbanApps. All rights reserved.",
                color = Color(0xFF6F6F71),
                fontSize = 8.sp
            )
            Text("Made with ❤️ by Aban", color = Color(0xFF6F6F71), fontSize = 8.sp)
            Spacer(modifier = Modifier.height(3.dp))
        }
    }


}

