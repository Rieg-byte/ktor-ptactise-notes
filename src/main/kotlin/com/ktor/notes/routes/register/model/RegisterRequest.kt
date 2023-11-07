package com.ktor.notes.routes.register.model

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val login: String,
    val password: String,
    val username: String,
    val email: String
) {
    fun isBlankFields(): Boolean = login.isBlank() || password.isBlank() || username.isBlank()
}