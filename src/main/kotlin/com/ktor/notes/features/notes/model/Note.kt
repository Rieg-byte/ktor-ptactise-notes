package com.ktor.notes.features.notes.model

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val user: String,
    val noteId: String,
    val title: String,
    val content: String
)

val listOfNotes = mutableListOf(
    Note(
        user = "user",
        noteId = "239",
        title = "Моя заметка",
        content = "Не забыть завтра купить хлеб в продуктовом магазине."
    )
)