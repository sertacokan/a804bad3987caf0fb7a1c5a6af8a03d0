package com.example.spacedeliveryman.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.spacedeliveryman.database.station.SpaceStationEntity
import com.example.spacedeliveryman.repositories.FavoriteRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {

    val favoriteStations = favoriteRepository.getFavoriteStations().asLiveData()

    fun removeFromFavoriteStation(favoriteStationEntity: SpaceStationEntity) {
        viewModelScope.launch {
            favoriteRepository.removeFromFavorite(favoriteStationEntity)
        }
    }

}