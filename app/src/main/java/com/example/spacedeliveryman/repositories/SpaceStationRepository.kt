package com.example.spacedeliveryman.repositories

import com.example.spacedeliveryman.database.station.SpaceStationDao
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.network.SpaceStationResponseModel
import com.example.spacedeliveryman.network.SpaceStationService
import kotlinx.coroutines.flow.Flow
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sqrt

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

    fun convertToEntities(spaceStations: List<SpaceStationResponseModel>): List<SpaceStationEntity> {

        val earth = spaceStations.find { it.name == "Dünya" }

        return spaceStations.map { station ->

            val distanceX = earth?.coordinateX?.minus(station.coordinateX)?.absoluteValue?.toDouble() ?: 0.0
            val distanceY = earth?.coordinateY?.minus(station.coordinateY)?.absoluteValue?.toDouble() ?: 0.0
            val distance = sqrt(distanceX.pow(2) + distanceY.pow(2))

            SpaceStationEntity(0, station.name, distance, station.coordinateX, station.coordinateY, station.capacity, station.stock, station.need)
        }

    }

}