package com.ktor.notes.routes.login.model


import kotlinx.serialization.Serializable

@Serializable
data class LoginReceive(
    val login: String,
    val password: String
)
