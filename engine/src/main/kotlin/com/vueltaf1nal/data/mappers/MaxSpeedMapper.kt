package com.vueltaf1nal.data.mappers

import com.vueltaf1nal.data.dtos.MaxSpeedDto
import com.vueltaf1nal.domain.models.MaxSpeed

fun MaxSpeed.toDto(): MaxSpeedDto = MaxSpeedDto(
    name = driverName,
    topSpeed = topSpeed,
    speedLap = lap
)

fun MaxSpeedDto.toDomain(): MaxSpeed = MaxSpeed(
    driverName = name,
    topSpeed = topSpeed,
    lap = speedLap
)
