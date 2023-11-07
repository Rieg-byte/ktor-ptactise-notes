package com.ktor.notes.data.users

import com.ktor.notes.routes.register.model.RegisterRequest

interface UsersDao {
    suspend fun insertUser(user: RegisterRequest): RegisterRequest?
    suspend fun getUser(login: String): RegisterRequest?
    suspend fun checkUserWithEmailExists(email: String): Boolean
}