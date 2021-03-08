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
package com.example.androiddevchallenge.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import dev.chrisbanes.accompanist.insets.navigationBarsPadding
import dev.chrisbanes.accompanist.insets.statusBarsPadding

@ExperimentalAnimationApi
@Preview("Numbers Preview", widthDp = 360, heightDp = 640)
@Composable
fun NumbersPreview() {
    CountdownTimerTheme(darkTheme = true) {
        Numbers()
    }
}

@ExperimentalAnimationApi
@Composable
fun Numbers() {

    val points: List<Offset> = listOf(
        Offset(200f, 200f), Offset(400f, 200f),
        Offset(200f, 200f), Offset(200f, 400f),
        Offset(400f, 200f), Offset(400f, 400f),
        Offset(200f, 400f), Offset(400f, 400f),
        Offset(200f, 400f), Offset(200f, 600f),
        Offset(200f, 600f), Offset(400f, 600f),
        Offset(400f, 400f), Offset(400f, 600f),
    )

    AnimatedVisibility(
        initiallyVisible = false, visible = true,
        modifier = Modifier.animateContentSize()
    ) {

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
                .animateContentSize()
        ) {

            val path = Path()

            path.moveTo(400f, 1000f)

            drawPoints(
                points = points,
                pointMode = PointMode.Lines,
                color = Color.Gray,
                strokeWidth = 16f,
                cap = StrokeCap.Round,
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(8f, 16f))
            )
            path.moveTo(400f, 800f)
        }
    }
}
