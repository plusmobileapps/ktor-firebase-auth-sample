package com.plusmobileapps.firebaseadminsample.util

import io.ktor.server.resources.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*

fun ApplicationTestBuilder.testRouting(block: Routing.() -> Unit) {
    install(Resources)
    routing { block() }
}