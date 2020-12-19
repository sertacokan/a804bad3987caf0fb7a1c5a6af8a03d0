package com.example.spacedeliveryman.utils

import com.example.spacedeliveryman.database.station.SpaceStationEntity

interface FavoriteSelectionListener {
    fun onFavoriteClicked(spaceStationEntity: SpaceStationEntity)
}