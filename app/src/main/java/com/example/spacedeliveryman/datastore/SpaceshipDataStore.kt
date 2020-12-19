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

    //Dayanıklık Süresi(DS)’ne göre belirli sürelerde hasarın 10 birim olarak azalmalıdır.
    suspend fun damageSpaceship(damage: Int = 10) {
        dataStore.updateData { preference ->
            val durability = preference.durability - damage
            preference.toBuilder().setDurability(durability).build()
        }
    }

}