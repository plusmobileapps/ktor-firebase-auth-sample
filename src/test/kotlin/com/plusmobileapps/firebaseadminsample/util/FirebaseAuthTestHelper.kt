package com.plusmobileapps.firebaseadminsample.util

import com.plusmobileapps.firebaseadminsample.firebase.FIREBASE_AUTH
import com.plusmobileapps.firebaseadminsample.firebase.FirebaseJWTAuthKey
import com.plusmobileapps.firebaseadminsample.firebase.User
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.testing.*

class FirebaseAuthTestProvider(config: FirebaseTestConfig) : AuthenticationProvider(config) {

    private val authFunction: () -> User? = config.mockAuthFunction

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

    var mockAuthFunction: () -> User? = { null }

    fun mockAuthentication(mockUser: () -> User?) {
        mockAuthFunction = mockUser
    }

}

fun ApplicationTestBuilder.mockAuthentication(mockAuth: () -> User?) {
    install(Authentication) {
        firebaseTest {
            mockAuthentication { mockAuth() }
        }
    }
}

private fun AuthenticationConfig.firebaseTest(
    name: String? = FIREBASE_AUTH,
    configure: FirebaseTestConfig.() -> Unit
) {
    val provider = FirebaseAuthTestProvider(FirebaseTestConfig(name).apply(configure))
    register(provider)
}