package com.ktor.notes.data.notes

import org.jetbrains.exposed.sql.Table

object Notes: Table("notes") {
    val id = Notes.varchar("id", 100)
    val user = Notes.varchar("user", 25)
    val title = Notes.varchar("title", 50)
    val content = Notes.varchar("content", 500)
}