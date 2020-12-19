package com.example.spacedeliveryman.database.station

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceStationDao {

    @Query("SELECT * FROM SpaceStationTable")
    fun getAllStation(): Flow<List<SpaceStationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllStation(stations: List<SpaceStationEntity>)

    @Query("SELECT * FROM SpaceStationTable WHERE StationName LIKE '%' || :stationName || '%'")
    fun searchSpaceStation(stationName: String): Flow<List<SpaceStationEntity>>

    @Query("SELECT * FROM SpaceStationTable WHERE IsFavorite = 1")
    fun getFavoriteStations(): Flow<List<SpaceStationEntity>>

    @Query("SELECT * FROM SpaceStationTable WHERE IsActive = 1")
    fun getCurrentStation(): Flow<SpaceStationEntity>

    @Update
    suspend fun updateStation(spaceStationEntity: SpaceStationEntity)
}