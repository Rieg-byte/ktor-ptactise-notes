package com.ktor.notes.data.users

interface UsersDao {
    suspend fun insertUser(user: User): User?
}