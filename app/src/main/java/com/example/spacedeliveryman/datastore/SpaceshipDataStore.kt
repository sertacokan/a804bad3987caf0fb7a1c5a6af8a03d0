package com.example.spacedeliveryman.datastore

import androidx.datastore.DataStore
import com.example.spacedeliveryman.Spaceship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class SpaceshipDataStore(private val dataStore: DataStore<Spaceship>) {

    fun getSpaceshipData(): Flow<Spaceship> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                Spaceship.getDefaultInstance()
            } else {
                throw exception
            }
        }

    }

    suspend fun saveSpaceshipData(shipName: String, shipCapacity: Int, shipSpeed: Int, shipDurability: Int) {
        dataStore.updateData { preference ->
            preference
                .toBuilder()
                .setName(shipName)
                .setCapacity(shipCapacity)
                .setDamage(100)
                .setDurability(shipDurability)
                .setSpeed(shipSpeed)
                .build()
        }
    }

    suspend fun updateSpaceshipName(shipName: String) {
        dataStore.updateData { preference ->
            preference.toBuilder().setName(shipName).build()
        }
    }

}