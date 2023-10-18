package com.ktor.notes.data.users

import com.ktor.notes.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

class UsersDaoImpl : UsersDao {
    private fun resultRowToUser(row: ResultRow) = User(
        login = row[Users.login],
        password = row[Users.password],
        username = row[Users.username],
        email = row[Users.password]
    )
    override suspend fun insertUser(user: User) = dbQuery {
        val insertStatement = Users.insert{
            it[login] = user.login
            it[password] = user.password
            it[username] = user.username
            it[email] = user.email ?: ""
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }
}