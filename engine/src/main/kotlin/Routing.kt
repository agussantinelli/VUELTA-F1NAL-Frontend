package com.vueltaf1nal

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class DriverResultDto(
    val pos: Int,
    val name: String,
    val team: String,
    val time: String
)

@Serializable
data class MaxSpeedDto(
    val name: String,
    val topSpeed: Double,
    val speedLap: Int
)

fun Application.configureRouting() {
    val results = listOf(
        DriverResultDto(1, "Max Verstappen", "Red Bull Racing", "1:23:45.678"),
        DriverResultDto(2, "Lando Norris", "McLaren", "+2.456s"),
        DriverResultDto(3, "Charles Leclerc", "Ferrari", "+5.123s"),
        DriverResultDto(4, "Oscar Piastri", "McLaren", "+12.678s"),
        DriverResultDto(5, "Lewis Hamilton", "Mercedes", "+15.901s")
    )

    val topSpeeds = listOf(
        MaxSpeedDto("Lando Norris", 328.1, 56),
        MaxSpeedDto("Max Verstappen", 325.4, 44),
        MaxSpeedDto("Oscar Piastri", 324.5, 48),
        MaxSpeedDto("Charles Leclerc", 322.9, 52),
        MaxSpeedDto("Lewis Hamilton", 321.2, 58)
    )

    routing {
        get("/") {
            call.respondText("🏁 VUELTA F1NAL: Motor encendido y listo.")
        }

        get("/api/results") {
            call.respond(results)
        }

        get("/api/max-speed") {
            call.respond(topSpeeds)
        }
    }
}