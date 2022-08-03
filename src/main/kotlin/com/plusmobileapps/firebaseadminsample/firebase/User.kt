package com.plusmobileapps.firebaseadminsample.firebase

import io.ktor.server.auth.Principal

data class User(val userId: String = "", val displayName: String = "") : Principal
