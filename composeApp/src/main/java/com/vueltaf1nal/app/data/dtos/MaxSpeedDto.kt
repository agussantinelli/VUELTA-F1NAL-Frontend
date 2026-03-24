package com.vueltaf1nal.app.data.dtos

import kotlinx.serialization.Serializable

@Serializable
data class MaxSpeedDto(
    val name: String,
    val topSpeed: Double,
    val speedLap: Int
)
