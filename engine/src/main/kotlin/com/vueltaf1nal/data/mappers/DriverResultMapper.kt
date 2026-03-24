package com.vueltaf1nal.data.mappers

import com.vueltaf1nal.data.dtos.DriverResultDto
import com.vueltaf1nal.domain.models.DriverResult

fun DriverResult.toDto(): DriverResultDto = DriverResultDto(
    pos = position,
    name = driverName,
    team = teamName,
    time = gap
)

fun DriverResultDto.toDomain(): DriverResult = DriverResult(
    position = pos,
    driverName = name,
    teamName = team,
    gap = time
)
