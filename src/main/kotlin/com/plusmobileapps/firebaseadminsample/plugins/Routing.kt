package com.plusmobileapps.firebaseadminsample.plugins

import com.plusmobileapps.firebaseadminsample.routes.authenticatedRoute
import com.plusmobileapps.firebaseadminsample.routes.rootRoute
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources) // type safe routing
    routing {
        rootRoute()
        authenticatedRoute()
    }
}
