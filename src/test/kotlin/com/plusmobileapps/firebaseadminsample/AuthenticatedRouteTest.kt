package com.plusmobileapps.firebaseadminsample

import com.plusmobileapps.firebaseadminsample.firebase.User
import com.plusmobileapps.firebaseadminsample.routes.authenticatedRoute
import com.plusmobileapps.firebaseadminsample.util.mockAuthentication
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class AuthenticatedRouteTest {

    @Test
    fun `authenticated route - is authenticated`() = testApplication {
        val user = User("some id", "Andrew")
        mockAuthentication { user }
        routing { authenticatedRoute() }

        client.get("/authenticated").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("User is authenticated: $user", bodyAsText())
        }
    }

    @Test
    fun `authenticated route - is unauthorized`() = testApplication {
        mockAuthentication { null }
        routing { authenticatedRoute() }

        client.get("/authenticated").apply {
            assertEquals(HttpStatusCode.Unauthorized, status)
        }
    }
}