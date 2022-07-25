package com.example.kotlin_sqlite.kotlin_sqlite.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_sqlite.databinding.ActivityMainBinding
import com.example.kotlin_sqlite.kotlin_sqlite.database.User
import com.example.kotlin_sqlite.kotlin_sqlite.viewmodel.SqliteViewModel
import kotlinx.coroutines.DelicateCoroutinesApi

class MainActivity : AppCompatActivity() {

    lateinit var sqliteViewModel: SqliteViewModel
    lateinit var binding: ActivityMainBinding
    lateinit var sqliteDataAdapter: SqliteDataAdapter

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initview()
        binding.addUserDia.addUserBtn.setOnClickListener {
            addUserOnclick()
        }
    }

    fun initview() {
        sqliteViewModel = SqliteViewModel(applicationContext)
        sqliteDataAdapter = SqliteDataAdapter(applicationContext, supportFragmentManager, sqliteViewModel)
        sqliteDataAdapter.submitList(sqliteViewModel.getList())
        binding.recycler.apply {
            adapter = sqliteDataAdapter
            layoutManager = LinearLayoutManager(applicationContext)
        }
        sqliteViewModel.userRepository.change.observe(this)
        {
            sqliteDataAdapter.submitList(sqliteViewModel.getList())

        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun addUserOnclick() {
        if (binding.addUserDia.editFname.text.toString().isEmpty()) {
            binding.addUserDia.editFname.error = "Please fill it"
            return
        } else if (binding.addUserDia.editLname.text.toString().isEmpty()) {
            binding.addUserDia.editLname.error = "Please fill it"
            return
        } else if (binding.addUserDia.editAge.text.toString().isEmpty()) {
            binding.addUserDia.editAge.error = "Please fill it"
            return
        } else {
            val fName = binding.addUserDia.editFname.text.toString()
            val lName = binding.addUserDia.editLname.text.toString()
            val age = binding.addUserDia.editAge.text.toString()

            sqliteViewModel.insertUser(
                User(0, fName, lName, age.toInt())
            )
            showPopUp()

            Toast.makeText(
                applicationContext,
                "User has been added to Room database",
                Toast.LENGTH_SHORT
            ).show()
            binding.addUserDia.editFname.setText("")
            binding.addUserDia.editLname.setText("")
            binding.addUserDia.editAge.setText("")

        }

    }

    fun showPopUp() {
        val userAddedDialog = UserAddedDialog()
        userAddedDialog.show(supportFragmentManager, "t")

    }
}