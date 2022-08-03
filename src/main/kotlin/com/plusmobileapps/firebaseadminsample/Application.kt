package com.plusmobileapps.firebaseadminsample

import com.plusmobileapps.firebaseadminsample.firebase.FirebaseAdmin
import com.plusmobileapps.firebaseadminsample.plugins.configureFirebaseAuth
import com.plusmobileapps.firebaseadminsample.plugins.configureRouting
import com.plusmobileapps.firebaseadminsample.plugins.configureSerialization
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        FirebaseAdmin.init()
        configureSerialization()
        configureFirebaseAuth()
        configureRouting()
    }.start(wait = true)
}
