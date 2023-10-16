package com.ktor.notes.plugins

import com.ktor.notes.auth.JwtService
import com.ktor.notes.features.login.model.listOfLogin
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun Application.configureSecurity() {
    val jwtRealm = "ktor sample app"
    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(JwtService.getVerifier())
            validate {
                val login = it.payload.getClaim("username").asString()
                val log = listOfLogin.find { user -> user.username == login }
                if (log == null) {
                    null;
                } else {
                    JWTPrincipal(it.payload)
                }
            }
        }
    }
}
