package com.vueltaf1nal.app.data.repositories

import com.vueltaf1nal.app.domain.models.MaxSpeed

interface TopSpeedsRepository {
    suspend fun getTopSpeeds(): List<MaxSpeed>
}
