package com.ktor.notes.features.login

import com.ktor.notes.auth.JwtService
import com.ktor.notes.features.login.model.Login
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.loginRouting() {
    post("/login") {
        val login = call.receive<Login>()
        val token = JwtService.generateToken(login)
        call.respond(HttpStatusCode.OK, token)
    }
}