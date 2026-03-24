package com.vueltaf1nal.app.data.remote

import com.vueltaf1nal.app.data.dtos.MaxSpeedDto
import io.ktor.client.call.*
import io.ktor.client.request.*

class TopSpeedsService {
    suspend fun getTopSpeeds(): List<MaxSpeedDto> {
        return NetworkClient.client.get("${NetworkClient.BASE_URL}/api/max-speed").body()
    }
}
