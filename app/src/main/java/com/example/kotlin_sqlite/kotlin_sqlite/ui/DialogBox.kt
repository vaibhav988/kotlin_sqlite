package com.example.kotlin_sqlite.kotlin_sqlite.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

import com.example.kotlin_roomdb.database.User
import com.example.kotlin_sqlite.kotlin_sqlite.viewmodel.RoomViewModel
import com.example.kotlin_sqlite.databinding.UsereditdialogBinding
import com.example.kotlin_sqlite.kotlin_sqlite.repository.UserRepository


class DialogBox(context: Context, val user: User) : DialogFragment() {

    val roomviewmodel: RoomViewModel = RoomViewModel(context)
    lateinit var binding: UsereditdialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = UsereditdialogBinding.inflate(LayoutInflater.from(context))
        setPrperties()
        return binding.root.rootView
    }
    fun setPrperties() {
        binding.diaAge.setText(user.age.toString())
        binding.diaFname.setText(user.firstName)
        binding.diaLname.setText(user.lastName)

        binding.UpdateUserbtn.setOnClickListener {

            roomviewmodel.updateUser(
                User(
                    user.Id, binding.diaFname.text.toString(), binding.diaLname.text.toString(),
                    (binding.diaAge.text.toString()).toInt()
                )
            )
            dismiss()
        }
    }
}