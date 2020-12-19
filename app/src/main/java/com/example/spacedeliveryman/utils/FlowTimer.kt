package com.example.spacedeliveryman.utils

import android.os.CountDownTimer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlin.concurrent.timerTask

@ExperimentalCoroutinesApi
fun flowTimer(countDown: Long, repeatCount: Int = 10): Flow<TimerState> = callbackFlow {

    var currentRepeatCount = 0

    val timer = object : CountDownTimer(countDown, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val remainingTime = (millisUntilFinished / 1000).toInt()
            offer(TimerTicked(remainingTime = remainingTime))
        }

        override fun onFinish() {
            currentRepeatCount += 1
            offer(TimerFinished)
        }
    }

    if (currentRepeatCount == repeatCount) {
        cancel()
    }

    ensureActive()
    timer.start()

    invokeOnClose {
        timer.cancel()
    }
}


sealed class TimerState

data class TimerTicked(val remainingTime: Int)
object TimerFinished : TimerState()