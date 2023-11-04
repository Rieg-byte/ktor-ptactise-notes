package com.ktor.notes

import com.ktor.notes.data.users.UsersDaoImpl
import com.ktor.notes.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val usersDaoImpl = UsersDaoImpl()
    DatabaseFactory.initializationDatabase()
    configureSecurity(usersDaoImpl)
    configureSerialization()
    configureRouting()

}
