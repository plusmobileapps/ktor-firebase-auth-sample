package com.plusmobileapps.firebaseadminsample.routes

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

@Serializable
@Resource("/")
class RootRoute

fun Route.rootRoute() {
    get<RootRoute>() {
        call.respondText("Hello World, this is a public endpoint!")
    }
}