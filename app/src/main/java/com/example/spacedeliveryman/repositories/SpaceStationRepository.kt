package com.example.spacedeliveryman.repositories

import com.example.spacedeliveryman.database.station.SpaceStationDao
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.network.SpaceStationResponseModel
import com.example.spacedeliveryman.network.SpaceStationService
import kotlinx.coroutines.flow.Flow

class SpaceStationRepository(private val spaceStationService: SpaceStationService, private val spaceStationDao: SpaceStationDao) {

    fun getAllStations(): Flow<List<SpaceStationEntity>> {
        return spaceStationDao.getAllStation()
    }

    suspend fun getSpaceStations(): List<SpaceStationResponseModel> {
        return spaceStationService.getSpaceStationCoordinates()
    }

    suspend fun saveSpaceStations(spaceStationEntities: List<SpaceStationEntity>) {
        spaceStationDao.addAllStation(spaceStationEntities)
    }

    fun searchStation(stationName: String): Flow<List<SpaceStationEntity>> {
        return spaceStationDao.searchSpaceStation(stationName)
    }
}