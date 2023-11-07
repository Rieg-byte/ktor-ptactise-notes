package com.ktor.notes.routes.notes


import com.ktor.notes.data.notes.NotesDaoImpl
import com.ktor.notes.data.notes.mapToNoteDTO
import com.ktor.notes.routes.notes.model.NoteRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.notesRouting(notesDao: NotesDaoImpl) {
    route("/notes") {
        get {
            try {
                val principal = call.principal<JWTPrincipal>()
                val user = principal!!.payload.getClaim("login").asString()
                val result = notesDao.getAllNotes(user)
                call.respond(result ?: emptyList())
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, "Неизвестная ошибка.")
            }
        }
        post {
            try {
                val principal = call.principal<JWTPrincipal>()
                val user = principal!!.payload.getClaim("login").asString()
                val note = call.receive<NoteRequest>()
                notesDao.insertNote(note.mapToNoteDTO(user))
                call.respond(HttpStatusCode.OK, "Заметка успешно добавлена")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.Conflict, "Неизвестная ошибка.")
            }
        }
    }
}