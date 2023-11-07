package com.ktor.notes.routes.notes.model

import kotlinx.serialization.Serializable

@Serializable
data class NoteResponse(
    val id: String,
    val title: String?,
    val content: String
)

