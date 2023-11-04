package com.ktor.notes.plugins

import com.ktor.notes.auth.JwtService
import com.ktor.notes.data.users.UsersDaoImpl
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*


fun Application.configureSecurity(usersDaoImpl: UsersDaoImpl) {
    val jwtRealm = "ktor practise notes app"
    authentication {
        jwt("auth-jwt") {
            realm = jwtRealm
            verifier(JwtService.getVerifier())
            validate {
                val login = it.payload.getClaim("login").asString()
                val user = usersDaoImpl.getUser(login)
                if (user != null) {
                    JWTPrincipal(it.payload)
                } else {
                    null
                }

            }
        }
    }
}
