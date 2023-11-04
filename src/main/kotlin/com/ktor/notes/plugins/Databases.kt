package com.ktor.notes.plugins


import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import java.lang.System.getenv


object DatabaseFactory {
    fun initializationDatabase() {
        Database.connect(
            url = getenv("URL_DB"),
            driver = getenv("DRIVER_DB"),
            user = getenv("USER_DB"),
            password = getenv("PASSWORD_DB")
        )
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}