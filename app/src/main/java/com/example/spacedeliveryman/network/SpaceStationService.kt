package com.example.spacedeliveryman.network

import retrofit2.http.GET

interface SpaceStationService {

    @GET("e7211664-cbb6-4357-9c9d-f12bf8bab2e2")
    suspend fun getSpaceStationCoordinates(): List<SpaceStationResponseModel>

}