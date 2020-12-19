package com.example.spacedeliveryman.datastore

import androidx.datastore.DataStore
import com.example.spacedeliveryman.DeliveryRemainingData
import com.example.spacedeliveryman.Spaceship
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException

class DeliveryRemainingDataStore(private val dataStore: DataStore<DeliveryRemainingData>) {

    fun getDeliveryRemainingInfo(): Flow<DeliveryRemainingData> {
        return dataStore.data.catch { exception ->
            if (exception is IOException) {
                DeliveryRemainingData.getDefaultInstance()
            } else {
                throw  exception
            }
        }
    }

    suspend fun saveRemainingDeliveryInfos(spaceship: Spaceship) {
        dataStore.updateData { preferences ->
            preferences
                .toBuilder()
                .setUgs(spaceship.capacity * 10_000)
                .setEus(spaceship.speed * 20)
                .setDs(spaceship.durability * 10_000)
                .build()
        }
    }

    suspend fun updateUGS(ugs: Int) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().setUgs(ugs).build()
        }
    }

    suspend fun updateEUS(eus: Int) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().setEus(eus).build()
        }
    }

    suspend fun updateDS(ds: Int) {
        dataStore.updateData { preferences ->
            preferences.toBuilder().setDs(ds).build()
        }
    }
}