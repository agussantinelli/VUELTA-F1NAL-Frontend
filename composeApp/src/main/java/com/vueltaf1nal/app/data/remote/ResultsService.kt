package com.vueltaf1nal.app.data.remote

import com.vueltaf1nal.app.data.dtos.DriverResultDto
import io.ktor.client.call.*
import io.ktor.client.request.*

class ResultsService {
    suspend fun getResults(): List<DriverResultDto> {
        return NetworkClient.client.get("${NetworkClient.BASE_URL}/api/results").body()
    }
}
