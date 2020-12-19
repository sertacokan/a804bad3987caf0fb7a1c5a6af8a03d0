package com.example.spacedeliveryman.repositories

import com.example.spacedeliveryman.database.station.SpaceStationDao
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import kotlinx.coroutines.flow.Flow

class FavoriteRepository(private val spaceStationDao: SpaceStationDao) {

    fun getFavoriteStations(): Flow<List<SpaceStationEntity>> {
        return spaceStationDao.getFavoriteStations()
    }

    suspend fun addToFavorite(favoriteStationEntity: SpaceStationEntity) {
        spaceStationDao.updateStation(favoriteStationEntity.copy(isFavorite = 1))
    }

    suspend fun removeFromFavorite(favoriteStationEntity: SpaceStationEntity) {
        spaceStationDao.updateStation(favoriteStationEntity.copy(isFavorite = 0))
    }
}