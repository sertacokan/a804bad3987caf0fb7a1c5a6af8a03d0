package com.example.spacedeliveryman.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpaceStationResponseModel(
    val name: String,
    val coordinateX: Int,
    val coordinateY: Int,
    val capacity: Int,
    val stock: Int,
    val need: Int
)
