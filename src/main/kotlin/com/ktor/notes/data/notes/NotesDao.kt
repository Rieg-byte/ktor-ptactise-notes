package com.ktor.notes.data.notes

import com.ktor.notes.routes.notes.model.NoteResponse

interface NotesDao {
    suspend fun getAllNotes(user: String): List<NoteResponse>?
    suspend fun insertNote(noteDTO: NoteDTO)
}