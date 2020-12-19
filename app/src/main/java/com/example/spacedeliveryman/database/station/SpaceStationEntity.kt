package com.example.spacedeliveryman.database.station

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SpaceStationTable")
data class SpaceStationEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "StationId")
    val stationId: Int,
    @ColumnInfo(name = "StationName")
    val name: String,
    @ColumnInfo(name = "DistanceFromEarth")
    val distanceFromEarth: Double,
    @ColumnInfo(name = "CoordinationX")
    val coordinateX: Int,
    @ColumnInfo(name = "CoordinationY")
    val coordinateY: Int,
    @ColumnInfo(name = "Capacity")
    val capacity: Int,
    @ColumnInfo(name = "Stock")
    val stock: Int,
    @ColumnInfo(name = "Need")
    val need: Int,
    @ColumnInfo(name = "IsFavorite")
    val isFavorite: Int,
    @ColumnInfo(name = "IsCompleted")
    val isCompleted: Int,
    @ColumnInfo(name = "IsActive")
    val isActive: Int = 0
)
