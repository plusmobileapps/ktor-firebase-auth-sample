package com.plusmobileapps.firebaseadminsample.util

import com.plusmobileapps.firebaseadminsample.firebase.FIREBASE_AUTH
import com.plusmobileapps.firebaseadminsample.firebase.FirebaseJWTAuthKey
import com.plusmobileapps.firebaseadminsample.firebase.User
import io.ktor.server.auth.*
import io.ktor.server.testing.*

val defaultTestUser = User(userId = "some-user-id", displayName = "Darth Vader")

class FirebaseAuthTestProvider(config: FirebaseTestConfig) : AuthenticationProvider(config) {

    private val authFunction: () -> User? = config.mockAuthProvider

    override suspend fun onAuthenticate(context: AuthenticationContext) {
        val mockUser: User? = authFunction()
        if (mockUser != null) {
            context.principal(mockUser)
        } else {
            context.error(
                FirebaseJWTAuthKey,
                AuthenticationFailedCause.Error("User was mocked to be unauthenticated")
            )
        }
    }
}

class FirebaseTestConfig(name: String?) : AuthenticationProvider.Config(name) {

    var mockAuthProvider: () -> User? = { null }

}

fun ApplicationTestBuilder.mockAuthentication(mockAuth: () -> User? = { defaultTestUser }) {
    install(Authentication) {
        val provider = FirebaseAuthTestProvider(FirebaseTestConfig(FIREBASE_AUTH).apply {
            mockAuthProvider = mockAuth
        })
        register(provider)
    }
}
