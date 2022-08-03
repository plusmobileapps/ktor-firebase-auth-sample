package com.plusmobileapps.firebaseadminsample.plugins

import com.plusmobileapps.firebaseadminsample.routes.authenticatedRoute
import com.plusmobileapps.firebaseadminsample.routes.rootRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        rootRoute()
        authenticatedRoute()
    }
}
