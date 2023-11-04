package com.ktor.notes.features.notes


import com.ktor.notes.features.notes.model.listOfNoteResponses
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.notesRouting() {
    route("/notes") {
        get {
            call.respond(listOfNoteResponses)
        }
    }
}