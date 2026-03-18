package com.vueltaf1nal

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureAdministration()
    configureTemplating()
    configureSecurity()
    configureRouting()
}
