package com.vueltaf1nal.app.data.repositories

import com.vueltaf1nal.app.domain.models.DriverResult

interface ResultsRepository {
    suspend fun getDriverResults(): List<DriverResult>
}
