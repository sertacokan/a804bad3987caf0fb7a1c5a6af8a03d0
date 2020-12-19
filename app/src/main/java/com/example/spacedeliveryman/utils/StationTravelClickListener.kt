package com.example.spacedeliveryman.utils

import com.example.spacedeliveryman.database.station.SpaceStationEntity

interface StationTravelClickListener {
    fun onStationTravelClicked(spaceStationEntity: SpaceStationEntity)
}