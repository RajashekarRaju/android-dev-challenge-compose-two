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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@Preview("Hourglass Preview", widthDp = 360, heightDp = 640)
@Composable
fun HourglassPreview() {
    CountdownTimerTheme(darkTheme = true) {
        Hourglass()
    }
}

@Composable
fun Hourglass() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val lineColor = MaterialTheme.colors.onBackground

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            val canvasSize = size
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawRoundRect(
                size = canvasSize / 2F,
                cornerRadius = CornerRadius(60F, 60F),
                color = lineColor,
                topLeft = Offset(
                    x = canvasWidth / 4F,
                    y = canvasHeight / 3F
                ),
                style = Stroke(width = 16F)
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {

            // Left top
            drawLine(
                start = Offset(x = 340f, -20f),
                end = Offset(x = 340f, y = 140f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )

            // Left top diagonal
            drawLine(
                start = Offset(x = 340f, 140f),
                end = Offset(x = 460f, y = 260f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )

            // Left bottom diagonal
            drawLine(
                start = Offset(x = 460f, 260f),
                end = Offset(x = 340f, y = 380f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )

            // Left bottom
            drawLine(
                start = Offset(x = 340f, 380f),
                end = Offset(x = 340f, y = 540f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )

            // Right bottom
            drawLine(
                start = Offset(x = 740f, 380f),
                end = Offset(x = 740f, y = 540f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )

            // Right top diagonal
            drawLine(
                start = Offset(x = 740f, 140f),
                end = Offset(x = 620f, y = 260f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )

            // Right bottom diagonal
            drawLine(
                start = Offset(x = 620f, y = 260f),
                end = Offset(x = 740f, y = 380f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )

            // Right top
            drawLine(
                start = Offset(x = 740f, -20f),
                end = Offset(x = 740f, y = 140f),
                color = lineColor,
                strokeWidth = 16F,
                cap = StrokeCap.Round
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            val canvasSize = size
            val canvasWidth = size.width
            val canvasHeight = size.height

            drawRoundRect(
                size = canvasSize / 2F,
                cornerRadius = CornerRadius(60F, 60F),
                color = lineColor,
                topLeft = Offset(
                    x = canvasWidth / 4F,
                    y = canvasHeight / 3F
                ),
                style = Stroke(width = 16F)
            )
        }
    }
}
