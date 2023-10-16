package com.ktor.notes.plugins

import com.ktor.notes.auth.JwtService
import com.ktor.notes.features.login.model.listOfLogin
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun Application.configureSecurity() {
    val jwtRealm = "ktor practise notes app"
    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(JwtService.getVerifier())
            validate {
                val username = it.payload.getClaim("username").asString()
                val user = listOfLogin.find { user -> user.username == username }
                if (user != null) {
                    JWTPrincipal(it.payload)
                } else {
                    null
                }

            }
        }
    }
}
