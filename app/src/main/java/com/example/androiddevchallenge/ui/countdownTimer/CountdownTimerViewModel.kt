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

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CountdownTimerViewModel : ViewModel() {

    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long> = _currentTime

    private var countDownTimer: CountDownTimer? = null

    fun startCountdown(
        seconds: Long
    ) {
        if (countDownTimer == null) {

            object : CountDownTimer(seconds * ONE_SECOND, ONE_SECOND) {

                override fun onTick(millisUntilFinished: Long) {
                    _currentTime.value = millisUntilFinished / ONE_SECOND
                }

                override fun onFinish() {
                    _currentTime.value = FINISHED
                }
            }.start().also { countDownTimer = it }
        }
    }

    companion object {
        private const val FINISHED = 0L
        private const val ONE_SECOND = 1000L
    }
}
