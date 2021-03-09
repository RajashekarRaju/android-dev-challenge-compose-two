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

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.CountdownTimerTheme
import com.example.androiddevchallenge.ui.theme.grey100
import com.example.androiddevchallenge.ui.theme.grey900
import com.example.androiddevchallenge.uitls.colorInfiniteTransition

@ExperimentalAnimationApi
@Preview("Numbers Preview", widthDp = 360, heightDp = 640)
@Composable
fun NumbersPreview() {
    CountdownTimerTheme(darkTheme = true) {
        Numbers(1, 2, 3, 4)
    }
}

@ExperimentalAnimationApi
@Composable
fun Numbers(
    drawOne: Int?,
    drawTwo: Int?,
    drawThree: Int?,
    drawFour: Int?
) {

    val lineColor = colorInfiniteTransition(grey900, grey100, 500)

    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        DrawLines(lineColor, drawNumbers(drawOne))
        DrawLines(lineColor, drawNumbers(drawTwo))
        Spacer(modifier = Modifier.width(4.dp))
        DrawLines(lineColor, drawNumbers(drawThree))
        DrawLines(lineColor, drawNumbers(drawFour))
    }
}

private fun drawNumbers(
    currentNumber: Int?
): List<Pair<Offset, Offset>> {
    var drawNumber = one
    when (currentNumber) {
        1 -> drawNumber = one
        2 -> drawNumber = two
        3 -> drawNumber = three
        4 -> drawNumber = four
        5 -> drawNumber = five
        6 -> drawNumber = six
        7 -> drawNumber = seven
        8 -> drawNumber = eight
        9 -> drawNumber = nine
        0 -> drawNumber = zero
    }
    return drawNumber
}

@Composable
private fun DrawLines(
    lineColor: Color,
    number: List<Pair<Offset, Offset>>
) {
    Canvas(
        modifier = Modifier
            .width(80.dp)
            .offset(y = (-100).dp)
    ) {

        for (point in number) {
            drawLine(
                start = point.first,
                end = point.second,
                color = lineColor,
                strokeWidth = 8F,
                cap = StrokeCap.Round,
            )
        }
    }
}

private val topHorizontal = Pair(Offset(0f, 0f), Offset(200f, 0f))
private val middleHorizontal = Pair(Offset(0f, 200f), Offset(200f, 200f))
private val BottomHorizontal = Pair(Offset(0f, 400f), Offset(200f, 400f))

private val topStartVertical = Pair(Offset(0f, 0f), Offset(0f, 200f))
private val bottomStartVertical = Pair(Offset(0f, 200f), Offset(0f, 400f))
private val topEndVertical = Pair(Offset(200f, 0f), Offset(200f, 200f))
private val bottomEndVertical = Pair(Offset(200f, 200f), Offset(200f, 400f))

private val shortDiagonal = Pair(Offset(0f, 100f), Offset(100f, 0f))
private val middleVertical = Pair(Offset(100f, 0f), Offset(100f, 400f))
private val longDiagonal = Pair(Offset(100f, 400f), Offset(200f, 0f))

private val zero: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    topStartVertical,
    topEndVertical,
    bottomStartVertical,
    BottomHorizontal,
    bottomEndVertical,
)

private val one: List<Pair<Offset, Offset>> = listOf(
    middleVertical,
    BottomHorizontal,
    shortDiagonal,
)

private val two: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    topEndVertical,
    middleHorizontal,
    bottomStartVertical,
    BottomHorizontal,
)

private val three: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    topEndVertical,
    middleHorizontal,
    BottomHorizontal,
    bottomEndVertical,
)

private val four: List<Pair<Offset, Offset>> = listOf(
    topStartVertical,
    middleHorizontal,
    bottomEndVertical,
)

private val five: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    topStartVertical,
    middleHorizontal,
    bottomEndVertical,
    BottomHorizontal,
)

private val six: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    topStartVertical,
    middleHorizontal,
    bottomEndVertical,
    bottomStartVertical,
    BottomHorizontal,
)

private val seven: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    longDiagonal,
)

private val eight: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    topStartVertical,
    topEndVertical,
    middleHorizontal,
    bottomStartVertical,
    BottomHorizontal,
    bottomEndVertical,
)

private val nine: List<Pair<Offset, Offset>> = listOf(
    topHorizontal,
    topStartVertical,
    topEndVertical,
    middleHorizontal,
    bottomEndVertical,
)