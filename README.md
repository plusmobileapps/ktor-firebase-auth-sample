# Ktor Firebase Auth Sample 

This is a [Ktor](https://ktor.io/) server sample that configures [authentication](https://ktor.io/docs/authentication.html) for use with the [Firebase Admin SDK](https://firebase.google.com/docs/admin/setup). 

For a more detailed description on how this project was configured, please check out this [article](https://plusmobileapps.com/2022/08/02/ktor-firebase-auth.html).

## Basic Usage 

Install the Firebase plugin on your Ktor application: 

```kotlin
install(Authentication) {
    firebase {
        validate {
            // TODO look up user profile here to fill in any additional information
            User(it.uid, it.name)
        }
    }
}
```

Use the `authenticate { }` function to wrap endpoints which require authentication: 

```kotlin
authenticate(FIREBASE_AUTH) {
    get("/authenticated") {
        val user: User = call.principal() ?: return@get call.respond(HttpStatusCode.Unauthorized)
        call.respond("User is authenticated: $user")
    }
}
```

## Setup 

Before starting, follow these [instructions](https://cloud.google.com/firestore/docs/client/get-firebase) to create a new firebase project and enable authentication. Then click on the settings button in the side bar -> project settings -> service accounts tab -> generate a new private key which should then download a JSON file to your machine. 

Rename this file to `ktor-firebase-auth-adminsdk.json` and move it into this project under `src/main/resources/ktor-firebase-auth-adminsdk.json`

> NOTE: Do not check this file into your code base as this file should be kept secret! To do so, add the path to this file in your `.gitignore`.

## Run the server 

To run the server from the command line, run the following command. 

```
./gradlew run
```

Now you can make a get request to `http://0.0.0.0:8080/authenticated` with the [Firebase ID token](https://firebase.google.com/docs/auth/admin/verify-id-tokens#android) from the client as the authorization header.  

```
curl --location --request GET 'http://0.0.0.0:8080/authenticated' \
--header 'Authorization: Bearer insert-firebase-access-token'
```
