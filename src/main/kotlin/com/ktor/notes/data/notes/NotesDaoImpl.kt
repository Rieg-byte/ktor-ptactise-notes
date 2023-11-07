package com.ktor.notes.data.notes

import com.ktor.notes.routes.notes.model.NoteResponse
import com.ktor.notes.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class NotesDaoImpl : NotesDao {
    private fun resultRowToNoteResponse(row: ResultRow): NoteResponse = NoteResponse(
        id = row[Notes.id],
        title = row[Notes.title],
        content = row[Notes.content]
    )
    override suspend fun getAllNotes(user: String): List<NoteResponse>? = dbQuery {
        Notes
            .select{Notes.user eq user}
            .map(::resultRowToNoteResponse)
            .takeIf { it.isNotEmpty() }
    }

    override suspend fun insertNote(noteDTO: NoteDTO): Unit = dbQuery {
        Notes.insert {
            it[id] = noteDTO.id
            it[user] = noteDTO.user
            it[title] = noteDTO.title ?: ""
            it[content] = noteDTO.content
        }
    }
}