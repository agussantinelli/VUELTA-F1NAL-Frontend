package com.vueltaf1nal.app.data.models.api

import kotlinx.serialization.Serializable

@Serializable
data class DriverResultDto(
    val pos: Int,
    val name: String,
    val team: String,
    val time: String
)
