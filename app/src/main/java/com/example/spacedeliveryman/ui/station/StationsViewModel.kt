package com.example.spacedeliveryman.ui.station

import androidx.lifecycle.*
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.repositories.SpaceStationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StationsViewModel(private val spaceStationRepository: SpaceStationRepository) : ViewModel() {

    private val _allStations = spaceStationRepository.getAllStations().asLiveData()

    private val _activeStation: LiveData<SpaceStationEntity?> = spaceStationRepository.getActiveStation().asLiveData()

    val stationNameSearchText = MutableLiveData<String>()

    private val _searchStations = stationNameSearchText.switchMap { stationName ->
        spaceStationRepository.searchStation(stationName).asLiveData()
    }

    private val _stations = MediatorLiveData<List<SpaceStationEntity>>()

    val activeStationName = _activeStation.map { it?.name }

    init {
        viewModelScope.launch { manageStations() }

        _stations.addSource(_allStations) { entityList -> _stations.value = entityList }
        _stations.addSource(_searchStations) { entityList -> _stations.value = entityList }
    }

    val stations: LiveData<List<SpaceStationEntity>> = _stations

    fun completeDelivery(spaceStationEntity: SpaceStationEntity) {
        viewModelScope.launch {
            spaceStationRepository.completeStationDelivery(spaceStationEntity)
        }
    }

    fun addStationToFavorite(stationEntity: SpaceStationEntity) {
        viewModelScope.launch {
            spaceStationRepository.addToFavorite(stationEntity)
        }
    }

    private suspend fun manageStations() {

        val spaceStations = spaceStationRepository.getSpaceStations()

        if (spaceStations.isEmpty()) {
            val entityList = withContext(Dispatchers.Default) { spaceStationRepository.convertToEntities(spaceStations) }
            spaceStationRepository.saveSpaceStations(entityList)
        }
    }

}