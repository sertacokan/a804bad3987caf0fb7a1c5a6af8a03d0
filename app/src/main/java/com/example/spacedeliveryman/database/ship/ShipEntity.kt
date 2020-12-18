package com.example.spacedeliveryman.database.ship

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "SpaceshipTable")
data class ShipEntity(
    @ColumnInfo(name = "ShipName")
    val shipName: String,
    @ColumnInfo(name = "TotalDamage")
    val totalDamage: Int,
    @ColumnInfo(name = "Speed")
    val speed: Int,
    @ColumnInfo(name = "MaximumCapacity")
    val maximumCapacity: Int,
    @ColumnInfo(name = "Durability")
    val durability: Int
)
