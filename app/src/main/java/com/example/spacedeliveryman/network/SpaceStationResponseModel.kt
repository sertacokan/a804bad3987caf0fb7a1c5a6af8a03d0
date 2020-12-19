package com.example.spacedeliveryman.network

data class SpaceStationResponseModel(
    val name: String,
    val coordinateX: Int,
    val coordinateY: Int,
    val capacity: Int,
    val stock: Int,
    val need: Int
)
