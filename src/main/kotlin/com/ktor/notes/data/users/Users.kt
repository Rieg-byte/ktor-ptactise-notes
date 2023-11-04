package com.ktor.notes.data.users

import org.jetbrains.exposed.sql.Table

data class User(
    val login: String,
    val password: String,
    val username: String,
    val email: String?
)

object Users: Table("users") {
    val login = Users.varchar("login", 25)
    val password = Users.varchar("password", 25)
    val username = Users.varchar("username", 30)
    val email = Users.varchar("email", 30)
}