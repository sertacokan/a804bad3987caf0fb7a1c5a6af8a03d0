package com.example.spacedeliveryman.extensions

import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

infix fun Int?.hypotenuse(other: Int): Double {

    val first = this ?: 0
    val second = other

    val distanceX = (first - second).absoluteValue.toDouble()
    val distanceY = (first - second).absoluteValue.toDouble()
    return sqrt(distanceX.pow(2) + distanceY.pow(2))
}

infix fun Int.hypotenuse(other: Int): Double {

    val first = this
    val second = other

    val distanceX = (first - second).absoluteValue.toDouble()
    val distanceY = (first - second).absoluteValue.toDouble()
    return sqrt(distanceX.pow(2) + distanceY.pow(2))
}