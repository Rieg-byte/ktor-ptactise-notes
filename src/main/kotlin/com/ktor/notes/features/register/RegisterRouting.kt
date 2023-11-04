package com.ktor.notes.features.register

import com.ktor.notes.data.users.UsersDaoImpl
import com.ktor.notes.features.register.model.RegisterReceive
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Route.registerRouting(usersDaoImpl: UsersDaoImpl) {
    post("/register") {
        val registerReceive = call.receive<RegisterReceive>()
        try {
            usersDaoImpl.insertUser(registerReceive.registerFormToUser())
            call.respond(HttpStatusCode.OK)
        } catch (e: ExposedSQLException) {
            call.respond(HttpStatusCode.Conflict, "Пользователь уже существует.")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict, "Неизвестная ошибка.")
        }
    }
}