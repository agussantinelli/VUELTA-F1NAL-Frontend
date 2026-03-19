package com.vueltaf1nal

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
data class Telemetry(val driver: String, val speed: String, val lap: Int)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("🏁 VUELTA F1NAL: Motor encendido y listo.")
        }

        // Esta es la ruta que usaremos para la App
        get("/verstappen") {
            val data = Telemetry("Max Verstappen", "325 km/h", 44)
            call.respond(data)
        }
    }
}