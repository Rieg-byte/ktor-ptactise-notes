package com.ktor.notes.routes.register

import com.ktor.notes.data.users.UsersDaoImpl
import com.ktor.notes.routes.register.model.RegisterRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Route.registerRouting(usersDao: UsersDaoImpl) {
    post("/register") {
        val registerRequest = call.receiveNullable<RegisterRequest>() ?: return@post call.respond(
            HttpStatusCode.BadRequest,
        )
        if (usersDao.checkUserWithEmailExists(registerRequest.email)) {
            call.respond(HttpStatusCode.Conflict, "Пользователь с такой почтой уже существует.")
            return@post
        }
        if (registerRequest.isBlankFields()) {
            call.respond(HttpStatusCode.BadRequest, "Поля не заполнены.")
            return@post
        }
        try {
            usersDao.insertUser(registerRequest)
            call.respond(HttpStatusCode.OK)
        } catch (e: ExposedSQLException) {
            call.respond(HttpStatusCode.Conflict, "Пользователь уже существует.")
        } catch (e: Exception) {
            call.respond(HttpStatusCode.Conflict, "Неизвестная ошибка.")
        }
    }
}