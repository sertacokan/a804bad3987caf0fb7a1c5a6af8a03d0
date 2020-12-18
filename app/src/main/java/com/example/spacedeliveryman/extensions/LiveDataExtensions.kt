package com.example.spacedeliveryman.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

fun <F, S, T> LiveData<F>.combineTriple(second: LiveData<S>, third: LiveData<T>): LiveData<Triple<F?, S?, T?>> {
    return MediatorLiveData<Triple<F?, S?, T?>>().apply {

        var firstValue: F? = null
        var secondValue: S? = null
        var thirdValue: T? = null

        addSource(this@combineTriple) { f ->
            firstValue = f
            value = Triple(firstValue, secondValue, thirdValue)
        }

        addSource(second) { s ->
            secondValue = s
            value = Triple(firstValue, secondValue, thirdValue)
        }

        addSource(third) { t ->
            thirdValue = t
            value = Triple(firstValue, secondValue, thirdValue)
        }

    }
}


fun <F, S> LiveData<F>.combinePair(second: LiveData<S>): LiveData<Pair<F?, S?>> {
    return MediatorLiveData<Pair<F?, S?>>().apply {

        var firstValue: F? = null
        var secondValue: S? = null

        addSource(this@combinePair) { f ->
            firstValue = f
            value = Pair(firstValue, secondValue)
        }

        addSource(second) { s ->
            secondValue = s
            value = Pair(firstValue, secondValue)
        }
    }
}