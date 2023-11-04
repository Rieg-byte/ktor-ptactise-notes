package com.ktor.notes.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.lang.System.getenv
import java.util.*


object JwtService {
    private val issuer = "http://0.0.0.0:8080/"
    private val jwtSecret = getenv("SECRET_VAR")
    private val algorithm = Algorithm.HMAC256(jwtSecret)
    private val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun generateToken(login: String): String {
        return JWT.create()
            .withIssuer(issuer)
            .withClaim("login", login)
            .withExpiresAt(Date(System.currentTimeMillis() + 600000000))
            .sign(algorithm)
    }

    fun getVerifier(): JWTVerifier = verifier
}