package com.plusmobileapps.firebaseadminsample.plugins

import com.plusmobileapps.firebaseadminsample.firebase.FirebaseAdmin
import com.plusmobileapps.firebaseadminsample.firebase.User
import com.plusmobileapps.firebaseadminsample.firebase.firebase
import io.ktor.server.auth.*
import io.ktor.util.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*

fun Application.configureFirebaseAuth() {
    FirebaseAdmin.init()
    install(Authentication) {
        firebase {
            validate {
                // TODO look up user profile from DB
                User(it.uid, it.name)
            }
        }
    }
}
