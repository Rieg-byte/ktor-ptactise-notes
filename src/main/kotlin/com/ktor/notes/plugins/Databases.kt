package com.ktor.notes.plugins


import com.ktor.notes.data.users.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun initializationDatabase() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/notes",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "tseked02"
        )
        transaction {
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}