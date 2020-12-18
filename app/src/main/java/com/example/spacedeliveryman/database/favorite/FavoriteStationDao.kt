package com.example.spacedeliveryman.database.favorite

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteStationDao {

    @Query("SELECT * FROM FavoriteStationTable")
    fun favoriteStationList(): Flow<List<FavoriteStationEntity>>

    @Insert
    suspend fun addStationToFavorite(favoriteStationEntity: FavoriteStationEntity)

    @Delete
    suspend fun deleteStationFromFavorite(favoriteStationEntity: FavoriteStationEntity)
}