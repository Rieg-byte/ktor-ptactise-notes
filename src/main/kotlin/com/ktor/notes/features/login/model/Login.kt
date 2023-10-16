package com.ktor.notes.features.login.model


import kotlinx.serialization.Serializable

@Serializable
data class Login(
    val username: String,
    val password: String
)

val listOfLogin = mutableListOf(
    Login(
        username = "user",
        password = "qwerty"
    )
)