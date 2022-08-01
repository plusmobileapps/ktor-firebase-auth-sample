package com.plusmobileapps.firebaseadminsample

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.plusmobileapps.firebaseadminsample.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureFirebaseAuth()
        configureRouting()
    }.start(wait = true)
}
