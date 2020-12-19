package com.example.spacedeliveryman.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.spacedeliveryman.database.station.SpaceStationDao
import com.example.spacedeliveryman.database.station.SpaceStationEntity

@Database(entities = [SpaceStationEntity::class], version = 4)
abstract class SpaceDeliveryDatabase : RoomDatabase() {

    abstract fun spaceStationDao(): SpaceStationDao
}