/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.countdownTimer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.ui.theme.grey100
import com.example.androiddevchallenge.ui.theme.grey900
import com.example.androiddevchallenge.uitls.colorInfiniteTransition
import com.example.androiddevchallenge.uitls.scaleInfiniteTransition
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    CountdownTimerTheme(darkTheme = true) {
        CountdownTimer()
    }
}

@Composable
fun CountdownTimer(
    viewModel: CountdownTimerViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        var textVisible by remember { mutableStateOf(true) }

        if (textVisible) {

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {

                InfinitelyFlowingRing()

                Text(
                    text = stringResource(R.string.start_countdown_timer_title),
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .clip(CircleShape)
                        .border(1.dp, MaterialTheme.colors.onBackground, CircleShape)
                        .padding(horizontal = 20.dp, vertical = 44.dp)
                        .clickable {
                            textVisible = !textVisible
                        }
                )
            }
        } else {

            var countdownSetterVisible by remember { mutableStateOf(true) }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (!countdownSetterVisible) {
                    TextTimer(viewModel = viewModel)
                } else {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SetCountDownMinutes()
                        SetCountDownSeconds()
                    }

                    Button(
                        onClick = { countdownSetterVisible = !countdownSetterVisible },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp, vertical = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
                    ) {
                        Text(
                            text = stringResource(R.string.start_countdown_button),
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SetCountDownSeconds() {
    var seconds by rememberSaveable { mutableStateOf("00") }

    TextField(
        value = seconds,
        onValueChange = {
            seconds = it
            if (it.length > 2) seconds = seconds.takeLast(2)
        },
        label = null,
        placeholder = { Text("Sec") },
        textStyle = MaterialTheme.typography.h3,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
        maxLines = 1,
        modifier = Modifier
            .padding(24.dp)
            .size(80.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, Color.Gray, RectangleShape)
            .background(Color.Gray, RectangleShape)
    )
}

@Composable
fun SetCountDownMinutes() {
    var minutes by rememberSaveable { mutableStateOf("00") }

    TextField(
        value = minutes,
        onValueChange = {
            minutes = it
            if (it.length > 2) minutes = minutes.takeLast(2)
        },
        label = null,
        placeholder = { Text("Min") },
        textStyle = MaterialTheme.typography.h3,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
        ),
        maxLines = 1,
        modifier = Modifier
            .padding(24.dp)
            .size(80.dp)
            .clip(RoundedCornerShape(24.dp))
            .border(1.dp, Color.Gray, RectangleShape)
            .background(Color.Gray, RectangleShape)
    )
}

@Composable
private fun TextTimer(
    viewModel: CountdownTimerViewModel
) {
    val seconds: Long? by viewModel.currentTime.observeAsState()
    Text(
        text = seconds.toString(),
        style = MaterialTheme.typography.h3,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun InfinitelyFlowingRing() {

    val scaleOne = scaleInfiniteTransition(3f, 20f, 800)
    val colorOne = colorInfiniteTransition(grey900, grey100, 800)

    Icon(
        painter = painterResource(id = R.drawable.ic_radio_button),
        contentDescription = null,
        modifier = Modifier
            .graphicsLayer(
                scaleX = scaleOne,
                scaleY = scaleOne
            )
            .size(80.dp),
        tint = colorOne,
    )

    val scaleTwo = scaleInfiniteTransition(3f, 20f, 1200)
    val colorTwo = colorInfiniteTransition(grey900, grey100, 1200)

    Icon(
        painter = painterResource(id = R.drawable.ic_radio_button),
        contentDescription = null,
        modifier = Modifier
            .graphicsLayer(
                scaleX = scaleTwo,
                scaleY = scaleTwo
            )
            .size(80.dp),
        tint = colorTwo,
    )

    val scaleThree = scaleInfiniteTransition(3f, 20f, 1400)
    val colorThree = colorInfiniteTransition(grey900, grey100, 1400)

    Icon(
        painter = painterResource(id = R.drawable.ic_radio_button),
        contentDescription = null,
        modifier = Modifier
            .graphicsLayer(
                scaleX = scaleThree,
                scaleY = scaleThree
            )
            .size(80.dp),
        tint = colorThree,
    )
}
