package com.vueltaf1nal.data.repositories

import com.vueltaf1nal.domain.models.MaxSpeed

class SpeedRepository {
        private val hardcodedSpeeds =
                listOf(
                        MaxSpeed("Franco Colapinto", 328.1, 56),
                        MaxSpeed("Max Verstappen", 325.4, 44),
                        MaxSpeed("Pierre Gasly", 324.5, 48),
                        MaxSpeed("Charles Leclerc", 322.9, 52),
                        MaxSpeed("Oliver Bearman", 321.2, 58)
                )

        fun getAllTopSpeeds(): List<MaxSpeed> = hardcodedSpeeds
}
