package com.plusmobileapps.firebaseadminsample.plugins

import com.plusmobileapps.firebaseadminsample.firebase.User
import com.plusmobileapps.firebaseadminsample.firebase.firebase
import io.ktor.server.application.*
import io.ktor.server.auth.*

fun Application.configureFirebaseAuth() {
    install(Authentication) {
        firebase {
            validate {
                // TODO look up user profile from DB
                User(it.uid, it.name.orEmpty())
            }
        }
    }
}
