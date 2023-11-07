package com.ktor.notes.data.users

import com.ktor.notes.routes.register.model.RegisterRequest
import com.ktor.notes.plugins.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UsersDaoImpl : UsersDao {
    private fun resultRowToUser(row: ResultRow) = RegisterRequest(
        login = row[Users.login],
        password = row[Users.password],
        username = row[Users.username],
        email = row[Users.password]
    )

    override suspend fun insertUser(user: RegisterRequest) = dbQuery {
        val insertStatement = Users.insert{
            it[login] = user.login
            it[password] = user.password
            it[username] = user.username
            it[email] = user.email
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }

    override suspend fun getUser(login: String): RegisterRequest? = dbQuery {
        Users
            .select { Users.login eq login }
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun checkUserWithEmailExists(email: String): Boolean = dbQuery {
        Users
            .select{ Users.email eq email}
            .count() > 0
    }
}