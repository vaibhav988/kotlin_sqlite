package com.example.kotlin_sqlite.kotlin_sqlite.repository

import android.content.Context
import com.example.kotlin_sqlite.kotlin_sqlite.database.DatabaseService
import com.example.kotlin_sqlite.kotlin_sqlite.database.SqliteDataBase
import com.example.kotlin_sqlite.kotlin_sqlite.database.User

class UserRepository(val context: Context) {

    private val sqliteDataBaseInstance = DatabaseService.getDatabaseInstance(context)
    val change = SqliteDataBase.changed

    fun getUsers(): List<User> {
        return sqliteDataBaseInstance.getAll()
    }

    fun insertUser(user: User) {

        sqliteDataBaseInstance.insertUser(user)
    }

    fun update(user: User) {
        sqliteDataBaseInstance.update(user)

    }

    fun deleteUser(user: User) {
        sqliteDataBaseInstance.delete(user)

    }

}