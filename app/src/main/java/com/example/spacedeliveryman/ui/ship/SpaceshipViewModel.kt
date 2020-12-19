package com.example.spacedeliveryman.ui.ship

import androidx.lifecycle.*
import com.example.spacedeliveryman.extensions.combinePair
import com.example.spacedeliveryman.extensions.combineTriple
import com.example.spacedeliveryman.repositories.DataStoreRepository
import com.example.spacedeliveryman.ui.ship.models.SpaceshipScreenContinueAction
import com.example.spacedeliveryman.ui.ship.models.SpaceshipScreenEventActions
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch

class SpaceshipViewModel(private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    private val propertyTotalValue = 15

    val shipDurability = MutableLiveData(0)
    val shipSpeed = MutableLiveData(0)
    val shipCapacity = MutableLiveData(0)
    val shipName = MutableLiveData<String>()

    private val _durabilityMaxValue: LiveData<Pair<Int?, Int?>>
    private val _speedMaxValue: LiveData<Pair<Int?, Int?>>
    private val _capacityMaxValue: LiveData<Pair<Int?, Int?>>
    private val _buttonState: LiveData<Triple<Int?, Int?, Int?>>

    private val _screenActions = MutableLiveData<SpaceshipScreenEventActions>()

    val screenActions: LiveData<SpaceshipScreenEventActions> = _screenActions

    init {
        _durabilityMaxValue = shipSpeed.combinePair(shipCapacity)
        _speedMaxValue = shipDurability.combinePair(shipCapacity)
        _capacityMaxValue = shipDurability.combinePair(shipSpeed)
        _buttonState = shipSpeed.combineTriple(shipDurability, shipCapacity)
    }

    //region LiveData Map Methods
    val maxDurability = _durabilityMaxValue.map { combination -> calculateValue(combination.first, combination.second) }

    val maxSpeed = _speedMaxValue.map { combination -> calculateValue(combination.first, combination.second) }

    val maxCapacity = _capacityMaxValue.map { combination -> calculateValue(combination.first, combination.second) }

    val buttonState = _buttonState.map { combination -> combination.first != 0 && combination.second != 0 && combination.third != 0 }
    //endregion

    private fun calculateValue(first: Int?, second: Int?): Int {
        val firstValue = first ?: 0
        val secondValue = second ?: 0

        return propertyTotalValue - firstValue - secondValue
    }

    fun continueButtonClicked() {
        viewModelScope.launch { saveInfo() }.invokeOnCompletion { _screenActions.value = SpaceshipScreenContinueAction }
    }

    private suspend fun saveInfo() {
        val name = shipName.value ?: ""
        val speed = shipSpeed.value ?: 1
        val capacity = shipCapacity.value ?: 1
        val durability = shipDurability.value ?: 1
        dataStoreRepository.saveSpaceshipInfo(name, capacity, speed, durability)
        dataStoreRepository.saveRemainingData(capacity, speed, durability)
    }
}