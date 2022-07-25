package com.example.kotlin_sqlite.kotlin_sqlite.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kotlin_roomdb.database.User
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


const val DB_NAME = "SqliteDB1"
const val TABLE_NAME = "userTable"
const val COL_FNAME = "userFname"
const val COL_AGE = "userAge"
const val COL_ID = "userId"
const val COL_LNAME = "userLname"

class SqliteDataBase(private val context: Context) : SQLiteOpenHelper(context, DB_NAME, null, 1) {
    companion object {
        var changed: MutableLiveData<Int> = MutableLiveData()
    }

    override fun onCreate(db: SQLiteDatabase?) {

        val createQuery =
            "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " Integer PRIMARY KEY AUTOINCREMENT," +
                    COL_FNAME + " varchar(256), " + COL_LNAME + " varchar(256)," + COL_AGE + " Integer )"

        if (db != null) {
            db.execSQL(createQuery)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun changeChange() {
        val code = Math.random() * (changed.value ?: 1)
        changed.postValue(code.toInt())
    }

    fun insertUser(user: User) {   // insert user into sqlite
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_FNAME, user.firstName)
        cv.put(COL_LNAME, user.lastName)
        cv.put(COL_AGE, user.age)
        val resp = db.insert(TABLE_NAME, null, cv)
        if (resp != "-1".toLong()) {
            changeChange()

        }
        db.close()
    }

    fun getAll(): List<User> {   // fetch all users from sqlite

        var list = ArrayList<User>()
        val db = this.readableDatabase
        var query = "SELECT * FROM " + TABLE_NAME
        val result = db.rawQuery(query, null)

        while (result.moveToNext()) {
            val user = User(
                result.getInt(0).toInt(),
                result.getString(1),
                result.getString(2),
                result.getInt(3).toInt()
            )
            list.add(user)
        }
        result.close()
        db.close()
        return list
    }

    fun update(user: User) {

        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID, user.Id)
        cv.put(COL_FNAME, user.firstName)
        cv.put(COL_LNAME, user.lastName)
        cv.put(COL_AGE, user.age)
        var array = arrayOf(user.Id.toString())
        db.update(TABLE_NAME, cv, "$COL_ID=?", array)
        db.close()
        changeChange()
    }

    fun delete(user: User) {
        val db = this.writableDatabase
        val query = "DELETE FROM " + TABLE_NAME + " WHERE " + COL_ID + " = " + user.Id
        db.execSQL(query)
        db.close()
        changeChange()

    }


}