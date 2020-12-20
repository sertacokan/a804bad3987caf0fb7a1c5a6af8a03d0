package com.example.spacedeliveryman.repositories

import com.example.spacedeliveryman.database.station.SpaceStationDao
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.extensions.hypotenuse
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

    fun getActiveStation(): Flow<SpaceStationEntity> {
        return spaceStationDao.getCurrentStation()
    }

    suspend fun completeStationDelivery(spaceStationEntity: SpaceStationEntity) {
        spaceStationDao.updateStation(spaceStationEntity.copy(isCompleted = 1))
    }

    suspend fun addToFavorite(spaceStationEntity: SpaceStationEntity) {
        spaceStationDao.updateStation(spaceStationEntity.copy(isFavorite = 1))
    }

    suspend fun updateDistanceFromCurrentStation(entities: List<SpaceStationEntity>) {
        spaceStationDao.updateDistanceFromCurrentStation(entities)
    }

    fun convertToEntities(spaceStations: List<SpaceStationResponseModel>): List<SpaceStationEntity> {

        val earth = spaceStations.find { it.name == "Dünya" }

        return spaceStations.map { station ->

            val distance = earth?.coordinateX hypotenuse station.coordinateX

            val isActive = if (station.name == earth?.name) 1 else 0

            SpaceStationEntity(
                0, station.name, distance, station.coordinateX, station.coordinateY, station.capacity, station.stock,
                station.need, 0, 0, isActive, distance
            )
        }
    }

     fun travelToCurrentStation(entities: List<SpaceStationEntity>?, currentStation: SpaceStationEntity): List<SpaceStationEntity> {

        return entities?.map { station ->

            val distance = currentStation.coordinateX hypotenuse station.coordinateX

            if (station.isActive == 1) {
                station.copy(distanceFromActiveStation = distance, isActive = 0)
            } else {
                if (station.stationId == currentStation.stationId) {
                    station.copy(distanceFromActiveStation = distance, isActive = 1, need = 0, stock = currentStation.capacity, isCompleted = 1)
                } else {
                    station.copy(distanceFromActiveStation = distance)
                }
            }
        } ?: emptyList()
    }
}