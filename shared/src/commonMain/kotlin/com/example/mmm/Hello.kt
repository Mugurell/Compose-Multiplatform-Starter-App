package com.example.mmm

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mmm.MR
import dev.icerock.moko.resources.compose.painterResource

@Composable
fun Hello() {
    MaterialTheme {
        var greetingText by remember { mutableStateOf("Hello World!") }
        var showImage by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                greetingText = "Hello Compose Multiplatform!"
                showImage = !showImage
            }) {
                Text(greetingText)
            }

            Spacer(Modifier
                .animateContentSize()
                .height(if (showImage) 88.dp else 0.dp)
            )

            AnimatedVisibility(showImage) {
                Image(
                    painter = painterResource(imageResource = MR.images.compose_multiplatform),
                    contentDescription = "Compose Multiplatform icon"
                )
            }
        }
    }
}
