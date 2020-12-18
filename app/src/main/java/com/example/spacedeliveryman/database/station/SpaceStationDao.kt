package com.example.spacedeliveryman.database.station

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SpaceStationDao {

    @Query("SELECT * FROM SpaceStationTable")
    fun getAllStation(): Flow<SpaceStationEntity>

    @Insert
    suspend fun addAllStation(stations: List<SpaceStationEntity>)

    @Delete
    suspend fun deleteStation(spaceStationEntity: SpaceStationEntity)

    @Query("SELECT * FROM SpaceStationTable WHERE StationName LIKE :stationName")
    suspend fun searchSpaceStation(stationName: String): List<SpaceStationEntity>
}