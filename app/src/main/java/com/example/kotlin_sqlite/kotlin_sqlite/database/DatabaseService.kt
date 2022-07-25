package com.example.kotlin_sqlite.kotlin_sqlite.database

import android.content.Context


object DatabaseService {

    @Volatile
    private var DatabaseInstance: SqliteDataBase? = null

    fun getDatabaseInstance(context: Context): SqliteDataBase {
        if (DatabaseInstance == null) {
            synchronized(this)
            {
                DatabaseInstance = SqliteDataBase(context)
            }
        }
        return DatabaseInstance!!
    }


}