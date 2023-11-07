package com.ktor.notes.routes.notes.model

import kotlinx.serialization.Serializable

@Serializable
data class NoteRequest(
    val title: String?,
    val content: String
)