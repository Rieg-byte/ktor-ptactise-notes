package com.ktor.notes.features.login.model


import kotlinx.serialization.Serializable

@Serializable
data class LoginReceive(
    val login: String,
    val password: String
)
