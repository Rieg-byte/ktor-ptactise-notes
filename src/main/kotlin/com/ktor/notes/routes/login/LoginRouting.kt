package com.ktor.notes.routes.login

import com.ktor.notes.auth.JwtService
import com.ktor.notes.data.users.UsersDaoImpl
import com.ktor.notes.routes.login.model.LoginReceive
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.loginRouting(usersDaoImpl: UsersDaoImpl) {
    post("/login") {
        val loginReceive = call.receive<LoginReceive>()
        val user = usersDaoImpl.getUser(loginReceive.login)
        if (user == null) {
            call.respond(HttpStatusCode.NotFound, "Пользователь не найден.")
        } else {
            if (user.password == loginReceive.password) {
                val token = JwtService.generateToken(loginReceive.login)
                call.respond(token)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Неверный пароль")
            }
        }
    }
}