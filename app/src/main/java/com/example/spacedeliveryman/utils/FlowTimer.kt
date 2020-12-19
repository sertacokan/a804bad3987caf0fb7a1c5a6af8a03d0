package com.example.spacedeliveryman.utils

import android.os.CountDownTimer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun flowTimer(countDown: Long): Flow<TimerState> = callbackFlow {

    val timer = object : CountDownTimer(countDown, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            val remainingTime = (millisUntilFinished / 1000).toInt()
            offer(TimerTicked(remainingTime = remainingTime))
        }

        override fun onFinish() {
            offer(TimerFinished)
        }
    }

    timer.start()

    invokeOnClose {
        timer.cancel()
    }
}


sealed class TimerState

data class TimerTicked(val remainingTime: Int)
object TimerFinished : TimerState()