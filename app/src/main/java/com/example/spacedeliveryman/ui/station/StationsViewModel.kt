package com.example.spacedeliveryman.ui.station

import androidx.lifecycle.*
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.repositories.DataStoreRepository
import com.example.spacedeliveryman.repositories.SpaceStationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.min

class StationsViewModel(private val spaceStationRepository: SpaceStationRepository, private val dataStoreRepository: DataStoreRepository) : ViewModel() {

    val stationNameSearchText = MutableLiveData<String>()

    //region Station List
    private val _stations = MediatorLiveData<List<SpaceStationEntity>>()

    private val _allStations = spaceStationRepository.getAllStations().asLiveData()

    private val _searchStations = stationNameSearchText.switchMap { stationName ->
        spaceStationRepository.searchStation(stationName).asLiveData()
    }
    //endregion

    //region Active Station
    private val _activeStation: LiveData<SpaceStationEntity?> = spaceStationRepository.getActiveStation().asLiveData()

    val activeStationName = _activeStation.map { it?.name }
    //endregion

    //region Remaining Data
    private val remainingData = dataStoreRepository.getRemainingDataInfo().asLiveData()

    val ds = remainingData.map { it.ds }
    val ugs = remainingData.map { it.ugs }
    val eus = remainingData.map { it.eus }

    val counter = ds.map { it / 1000 }
    //endregion

    //region ShipInfo
    private val shipInfo = dataStoreRepository.getSpaceshipInfo().asLiveData()

    val shipName = shipInfo.map { it.name }

    val shipDamage = shipInfo.map { it.damage.toString() }
    //endregion

    init {
        viewModelScope.launch { manageStationsTransformation() }
        _stations.addSource(_allStations) { entityList -> _stations.value = entityList }
        _stations.addSource(_searchStations) { entityList -> _stations.value = entityList }
    }

    val stations: LiveData<List<SpaceStationEntity>> = _stations

    fun completeDelivery(spaceStationEntity: SpaceStationEntity) {
        viewModelScope.launch {
            spaceStationRepository.completeStationDelivery(spaceStationEntity)
            changeCurrentStation(spaceStationEntity)
        }
    }

    fun addStationToFavorite(stationEntity: SpaceStationEntity) {
        viewModelScope.launch {
            spaceStationRepository.addToFavorite(stationEntity)
        }
    }

    fun changeCurrentStation(stationEntity: SpaceStationEntity) {
        viewModelScope.launch {
            travelToCurrentStation(stationEntity)
            val stationList = stations.value ?: emptyList()
            val calculatedList = withContext(Dispatchers.Default) { spaceStationRepository.calculateDistanceFromCurrentStation(stationList, stationEntity) }
            spaceStationRepository.updateDistanceFromCurrentStation(calculatedList)
        }
    }

    private suspend fun manageStationsTransformation() {

        val spaceStations = spaceStationRepository.getSpaceStations()

        if (_allStations.value?.isEmpty() == true) {
            val entityList = withContext(Dispatchers.Default) { spaceStationRepository.convertToEntities(spaceStations) }
            spaceStationRepository.saveSpaceStations(entityList)
        }
    }

    private suspend fun travelToCurrentStation(stationEntity: SpaceStationEntity) {
        val deliveredUGS = min(stationEntity.need, stationEntity.stock)
        dataStoreRepository.travelToCurrentStation(stationEntity.distanceFromActiveStation.toInt(), deliveredUGS)
        spaceStationRepository.travelToCurrentStation(stationEntity, deliveredUGS)
    }

}