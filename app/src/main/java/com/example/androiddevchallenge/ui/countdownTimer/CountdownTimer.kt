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

import android.text.format.DateUtils
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.History
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ui.components.Hourglass
import com.example.androiddevchallenge.ui.components.InfinitelyFlowingRing
import com.example.androiddevchallenge.ui.components.Numbers
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.uitls.formatToInt
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@ExperimentalAnimationApi
@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
private fun DarkPreview() {
    CountdownTimerTheme(darkTheme = true) {
        StartCountFlow()
    }
}

@ExperimentalAnimationApi
@Composable
fun StartCountFlow(
    viewModel: CountdownTimerViewModel = viewModel()
) {
    var stateAnim by rememberSaveable { mutableStateOf(true) }
    var stateSetter by rememberSaveable { mutableStateOf(false) }
    var stateCount by rememberSaveable { mutableStateOf(false) }
    var saveSeconds by rememberSaveable { mutableStateOf("0") }
    val canvasCircleColor = MaterialTheme.colors.primaryVariant

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {

            if (stateAnim) {
                Hourglass()
                InfinitelyFlowingRing()
                BackgroundCanvas(canvasCircleColor)

                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter,
                ) {
                    Button(
                        onClick = {
                            stateAnim = !stateAnim
                            stateSetter = !stateSetter
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 48.dp, vertical = 16.dp),
                        shape = MaterialTheme.shapes.large,
                        colors = ButtonDefaults.buttonColors(MaterialTheme.colors.secondary)
                    ) {
                        Text(
                            text = stringResource(R.string.add_timer_button),
                            style = MaterialTheme.typography.h6,
                        )
                    }
                }
            }

            if (stateSetter) {

                Hourglass()

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    if (saveSeconds.isEmpty()) {
                        saveSeconds = stringResource(R.string.seconds_placeholder_text)
                    }
                    Text(
                        text = DateUtils.formatElapsedTime(saveSeconds.toLong()),
                        style = MaterialTheme.typography.h5,
                        color = MaterialTheme.colors.onBackground,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        saveSeconds = setCountDownSeconds().toString()
                    }

                    Button(
                        onClick = {
                            stateSetter = !stateSetter
                            stateCount = !stateCount
                        },
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

            if (stateCount) {

                InfinitelyFlowingRing()

                TextTimer(
                    viewModel = viewModel,
                    seconds = saveSeconds.toLong()
                )

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.BottomCenter,
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {

                        IconButton(
                            onClick = {
                                viewModel.stopCountdown()
                                stateAnim = !stateAnim
                                stateCount = !stateCount
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Done,
                                contentDescription = stringResource(R.string.content_desc_finish_countdown_button),
                                modifier = Modifier.size(60.dp),
                                tint = MaterialTheme.colors.secondary
                            )
                        }

                        IconButton(
                            onClick = {
                                viewModel.restartCountdown()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.History,
                                contentDescription = stringResource(R.string.content_desc_reset_countdown_button),
                                modifier = Modifier.size(60.dp),
                                tint = MaterialTheme.colors.secondary
                            )
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
private fun TextTimer(
    viewModel: CountdownTimerViewModel,
    seconds: Long
) {
    val liveCount: Long? by viewModel.currentTime.observeAsState()
    viewModel.startCountdown(seconds)

    val elapsedTime: String? = liveCount?.let {
        DateUtils.formatElapsedTime(it)
    }

    Numbers(
        drawOne = formatToInt(elapsedTime, 0),
        drawTwo = formatToInt(elapsedTime, 1),
        drawThree = formatToInt(elapsedTime, 3),
        drawFour = formatToInt(elapsedTime, 4),
    )
}

@Composable
private fun BackgroundCanvas(
    canvasCircleColor: Color
) {
    Canvas(
        modifier = Modifier
            .size(360.dp)
            .clip(CircleShape)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = canvasCircleColor,
            center = Offset(
                x = canvasWidth / 2,
                y = canvasHeight / 2
            ),
            radius = size.minDimension / 5,
            style = Stroke(
                16f,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 30f),
                )
            )
        )
    }
}

@Composable
private fun setCountDownSeconds(): String? {

    var timeUnit: String? by rememberSaveable { mutableStateOf("00") }

    TextField(
        value = timeUnit.toString(),
        onValueChange = {
            timeUnit = it
            if (it.length > 4) timeUnit = timeUnit.toString().takeLast(4)
        },
        label = null,
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
            .background(Color.Gray, RectangleShape),
    )

    return timeUnit
}
