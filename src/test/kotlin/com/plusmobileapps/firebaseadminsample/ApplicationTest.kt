package com.plusmobileapps.firebaseadminsample

import com.plusmobileapps.firebaseadminsample.routes.rootRoute
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {
    @Test
    fun testRoot() = testApplication {
        routing {
            rootRoute()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World, this is a public endpoint!", bodyAsText())
        }
    }
}