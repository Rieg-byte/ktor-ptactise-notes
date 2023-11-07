package com.ktor.notes.plugins


import com.ktor.notes.data.notes.NotesDaoImpl
import com.ktor.notes.data.users.UsersDaoImpl
import com.ktor.notes.routes.login.loginRouting
import com.ktor.notes.routes.notes.notesRouting
import com.ktor.notes.routes.register.registerRouting
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.resources.Resources
import io.ktor.server.routing.*

fun Application.configureRouting(usersDao: UsersDaoImpl, notesDao: NotesDaoImpl) {
    install(Resources)
    routing {
        loginRouting(usersDao)
        registerRouting(usersDao)
        authenticate("auth-jwt") {
            notesRouting(notesDao)
        }

    }
}

