package com.ktor.notes.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.ktor.notes.features.login.model.Login
import java.util.*

object JwtService {
    private val issuer = "test"
    private val jwtSecret = "secret"
    private val algorithm = Algorithm.HMAC256(jwtSecret)
    private val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(login: Login): String {
        return JWT.create()
            .withIssuer(issuer)
            .withClaim("username", login.username)
            .withExpiresAt(Date(System.currentTimeMillis() + 600000000))
            .sign(algorithm)
    }

    fun getVerifier(): JWTVerifier = verifier
}