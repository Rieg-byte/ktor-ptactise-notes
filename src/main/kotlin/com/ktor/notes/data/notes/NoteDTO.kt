package com.ktor.notes.data.notes

import com.ktor.notes.routes.notes.model.NoteRequest
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class NoteDTO(
    val id: String,
    val user: String,
    val title: String?,
    val content: String
)
fun NoteRequest.mapToNoteDTO(user: String): NoteDTO = NoteDTO(
    id = UUID.randomUUID().toString(),
    user = user,
    title = title,
    content = content
)