package com.example.spacedeliveryman.database.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "FavoriteStationTable")
data class FavoriteStationEntity(
    @ColumnInfo(name = "FavoriteStationName")
    val name: String,
    @ColumnInfo(name = "FavoriteStationCoordinationX")
    val coordinateX: Int,
    @ColumnInfo(name = "FavoriteStationCoordinationY")
    val coordinateY: Int,
    @ColumnInfo(name = "FavoriteCapacity")
    val capacity: Int,
    @ColumnInfo(name = "FavoriteStock")
    val stock: Int,
    @ColumnInfo(name = "FavoriteNeed")
    val need: Int

)
