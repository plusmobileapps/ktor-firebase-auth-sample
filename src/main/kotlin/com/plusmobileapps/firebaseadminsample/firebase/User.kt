package com.plusmobileapps.firebaseadminsample.firebase

import io.ktor.server.auth.*
import java.io.Serializable

data class User(val userId: String, val displayName: String): Serializable, Principal
