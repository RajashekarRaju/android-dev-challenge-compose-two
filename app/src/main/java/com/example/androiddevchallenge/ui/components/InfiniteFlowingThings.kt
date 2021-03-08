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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import com.example.androiddevchallenge.uitls.colorInfiniteTransition
import com.example.androiddevchallenge.uitls.scaleInfiniteTransition

@Composable
fun InfinitelyFlowingRing() {

    val scaleOne = scaleInfiniteTransition(3f, 20f, 800)
    val colorOne = colorInfiniteTransition(
        MaterialTheme.colors.surface,
        MaterialTheme.colors.onBackground,
        800
    )

    val scaleTwo = scaleInfiniteTransition(3f, 20f, 1200)
    val colorTwo = colorInfiniteTransition(
        MaterialTheme.colors.surface,
        MaterialTheme.colors.onBackground,
        1200
    )

    val scaleThree = scaleInfiniteTransition(3f, 20f, 1400)
    val colorThree = colorInfiniteTransition(
        MaterialTheme.colors.surface,
        MaterialTheme.colors.onBackground,
        1400
    )

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scaleOne
                scaleY = scaleOne
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = colorOne,
            center = Offset(
                x = canvasWidth / 2,
                y = canvasHeight / 2
            ),
            radius = size.minDimension / 6,
            style = Stroke(
                16f,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 20f),
                )
            )
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scaleTwo
                scaleY = scaleTwo
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = colorTwo,
            center = Offset(
                x = canvasWidth / 2,
                y = canvasHeight / 2
            ),
            radius = size.minDimension / 8,
            style = Stroke(
                16f,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 30f),
                )
            )
        )
    }

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                scaleX = scaleThree
                scaleY = scaleThree
            }
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawCircle(
            color = colorThree,
            center = Offset(
                x = canvasWidth / 2,
                y = canvasHeight / 2
            ),
            radius = size.minDimension / 10,
            style = Stroke(
                16f,
                pathEffect = PathEffect.dashPathEffect(
                    intervals = floatArrayOf(10f, 40f),
                )
            )
        )
    }
}
