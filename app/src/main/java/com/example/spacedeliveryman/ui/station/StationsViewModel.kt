package com.example.spacedeliveryman.ui.station

import androidx.lifecycle.*
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.network.SpaceStationResponseModel
import com.example.spacedeliveryman.repositories.SpaceStationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.abs
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

class StationsViewModel(private val spaceStationRepository: SpaceStationRepository) : ViewModel() {

    private val _allStations = spaceStationRepository.getAllStations().asLiveData()

    val stationNameSearchText = MutableLiveData<String>()

    private val _searchStations = stationNameSearchText.switchMap { stationName ->
        spaceStationRepository.searchStation(stationName).asLiveData()
    }

    private val _stations = MediatorLiveData<List<SpaceStationEntity>>()

    init {
        viewModelScope.launch {
            val spaceStations = spaceStationRepository.getSpaceStations()
            convertToEntities(spaceStations)
        }
        _stations.addSource(_allStations) { domainList -> _stations.value = domainList }
        _stations.addSource(_searchStations) { domainList -> _stations.value = domainList }
    }

    val stations: LiveData<List<SpaceStationEntity>> = _stations

    private suspend fun convertToEntities(spaceStations: List<SpaceStationResponseModel>) {

        val earth = spaceStations.find { it.name == "Dünya" }

        val entities = withContext(Dispatchers.Default) {
            spaceStations.map { station ->
                val distanceX = earth?.coordinateX?.minus(station.coordinateX)?.absoluteValue?.toDouble() ?: 0.0
                val distanceY = earth?.coordinateY?.minus(station.coordinateY)?.absoluteValue?.toDouble() ?: 0.0
                val distance = sqrt(distanceX.pow(2) + distanceY.pow(2))

                SpaceStationEntity(0, station.name, distance, station.coordinateX, station.coordinateY, station.capacity, station.stock, station.need)
            }
        }

        spaceStationRepository.saveSpaceStations(entities)
    }
}