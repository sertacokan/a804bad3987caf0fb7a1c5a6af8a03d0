package com.example.spacedeliveryman.ui.ship

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.spacedeliveryman.extensions.combinePair
import com.example.spacedeliveryman.extensions.combineTriple

class SpaceshipViewModel : ViewModel() {

    private val propertyTotalValue = 15

    val shipDurability = MutableLiveData<Int>()
    val shipSpeed = MutableLiveData<Int>()
    val shipCapacity = MutableLiveData<Int>()

    private val _durabilityMaxValue: LiveData<Pair<Int?, Int?>>
    private val _speedMaxValue: LiveData<Pair<Int?, Int?>>
    private val _capacityMaxValue: LiveData<Pair<Int?, Int?>>
    private val _buttonState: LiveData<Triple<Int?, Int?, Int?>>

    init {
        _durabilityMaxValue = shipSpeed.combinePair(shipCapacity)
        _speedMaxValue = shipDurability.combinePair(shipCapacity)
        _capacityMaxValue = shipDurability.combinePair(shipSpeed)
        _buttonState = shipSpeed.combineTriple(shipDurability, shipCapacity)
    }

    val maxDurability = _durabilityMaxValue.map { combination -> calculateValue(combination.first, combination.second) }

    val maxSpeed = _speedMaxValue.map { combination -> calculateValue(combination.first, combination.second) }

    val maxCapacity = _capacityMaxValue.map { combination -> calculateValue(combination.first, combination.second) }

    val buttonState = _buttonState.map { combination ->
        combination.first != 0 && combination.second != 0 && combination.third != 0
    }

    private fun calculateValue(first: Int?, second: Int?): Int {
        val firstValue = first ?: 0
        val secondValue = second ?: 0

        return propertyTotalValue - firstValue - secondValue
    }
}