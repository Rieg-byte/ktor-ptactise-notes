package com.ktor.notes.plugins


import com.ktor.notes.data.users.UsersDaoImpl
import com.ktor.notes.features.login.loginRouting
import com.ktor.notes.features.notes.notesRouting
import com.ktor.notes.features.register.registerRouting
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.resources.Resources
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(Resources)
    routing {
        val usersDao = UsersDaoImpl()
        loginRouting(usersDao)
        registerRouting(usersDao)
        authenticate("auth-jwt") {
            notesRouting()
        }

    }
}

