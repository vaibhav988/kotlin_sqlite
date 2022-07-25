package com.example.kotlin_sqlite.kotlin_sqlite.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin_roomdb.database.User
import com.example.kotlin_roomdb.ui.RoomDataAdapter
import com.example.kotlin_sqlite.kotlin_sqlite.database.DatabaseService
import com.example.kotlin_sqlite.kotlin_sqlite.database.SqliteDataBase
import com.example.kotlin_sqlite.kotlin_sqlite.database.SqliteDataBase.Companion.changed
import com.example.kotlin_sqlite.kotlin_sqlite.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.contracts.contract

class RoomViewModel(private val context: Context) : ViewModel() {

    val userRepository: UserRepository = UserRepository(context = context)

    fun getList(): List<User> {
        return userRepository.getUsers()
    }

    fun insertUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.insertUser(user)
        }

    }


    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.update(user)

        }

    }
}

