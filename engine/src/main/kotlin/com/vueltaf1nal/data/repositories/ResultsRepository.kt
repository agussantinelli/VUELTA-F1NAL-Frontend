package com.vueltaf1nal.data.repositories

import com.vueltaf1nal.domain.models.DriverResult

class ResultsRepository {
    private val hardcodedResults = listOf(
        DriverResult(1, "Max Verstappen", "Red Bull Racing", "1:23:45.678"),
        DriverResult(2, "Lando Norris", "McLaren", "+2.456s"),
        DriverResult(3, "Charles Leclerc", "Ferrari", "+5.123s"),
        DriverResult(4, "Oscar Piastri", "McLaren", "+12.678s"),
        DriverResult(5, "Lewis Hamilton", "Mercedes", "+15.901s")
    )

    fun getAllResults(): List<DriverResult> = hardcodedResults
}
