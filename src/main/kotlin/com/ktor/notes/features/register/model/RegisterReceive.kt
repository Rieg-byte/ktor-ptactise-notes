package com.ktor.notes.features.register.model

import com.ktor.notes.data.users.User
import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceive(
    val login: String,
    val password: String,
    val username: String,
    val email: String?
) {
    fun registerFormToUser() = User(
        login = login,
        password = password,
        username = username,
        email = email
    )
}