package com.example.spacedeliveryman.database.station

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "SpaceStationTable")
data class SpaceStationEntity(
    @ColumnInfo(name = "StationName")
    val name: String,
    @ColumnInfo(name = "CoordinationX")
    val coordinateX: Int,
    @ColumnInfo(name = "CoordinationY")
    val coordinateY: Int,
    @ColumnInfo(name = "Capacity")
    val capacity: Int,
    @ColumnInfo(name = "Stock")
    val stock: Int,
    @ColumnInfo(name = "Need")
    val need: Int
)
