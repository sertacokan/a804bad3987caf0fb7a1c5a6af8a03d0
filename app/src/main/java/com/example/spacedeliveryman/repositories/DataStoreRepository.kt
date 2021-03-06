package com.example.spacedeliveryman.repositories

import com.example.spacedeliveryman.DeliveryRemainingData
import com.example.spacedeliveryman.Spaceship
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.datastore.DeliveryRemainingDataStore
import com.example.spacedeliveryman.datastore.SpaceshipDataStore
import kotlinx.coroutines.flow.Flow

class DataStoreRepository(private val spaceshipDataStore: SpaceshipDataStore, private val remainingDataStore: DeliveryRemainingDataStore) {

    fun getSpaceshipInfo(): Flow<Spaceship> {
        return spaceshipDataStore.getSpaceshipData()
    }

    suspend fun saveSpaceshipInfo(shipName: String, shipCapacity: Int, shipSpeed: Int, shipDurability: Int) {
        spaceshipDataStore.saveSpaceshipData(shipName, shipCapacity, shipSpeed, shipDurability)
    }

    fun getRemainingDataInfo(): Flow<DeliveryRemainingData> {
        return remainingDataStore.getDeliveryRemainingInfo()
    }

    suspend fun saveRemainingData(capacity: Int, speed: Int, durability: Int) {
        remainingDataStore.saveRemainingDeliveryInfo(capacity, speed, durability)
    }

    suspend fun updateDS(ds: Int) {
        remainingDataStore.updateDS(ds)
    }

    suspend fun damageSpaceship(damage: Int) {
        spaceshipDataStore.damageSpaceship(damage)
    }

    suspend fun travelToCurrentStation(spaceStationEntity: SpaceStationEntity) {
        remainingDataStore.travelToCurrentStation(spaceStationEntity.need, spaceStationEntity.distanceFromActiveStation.toInt())
    }

}