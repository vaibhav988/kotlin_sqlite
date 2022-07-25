package com.example.kotlin_sqlite.kotlin_sqlite.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


import com.example.kotlin_sqlite.databinding.UserItemBinding

import com.example.kotlin_sqlite.kotlin_sqlite.database.User



import com.example.kotlin_sqlite.kotlin_sqlite.viewmodel.SqliteViewModel


class SqliteDataAdapter(
    private val context: Context,
    private val supportFragmentManager: FragmentManager,
    private val sqliteViewModel: SqliteViewModel
) : ListAdapter<User, SqliteDataAdapter.ViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding , context , sqliteViewModel , supportFragmentManager)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))

    }


    class ViewHolder(private val binding: UserItemBinding, private val context: Context,
                     private val sqliteViewModel: SqliteViewModel, val supportFragmentManager: FragmentManager) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.firstnameText.text = "Fname: " + user.firstName
            binding.lastnameText.text = "Lname: " + user.lastName
            binding.ageText.text = "Age: " + user.age
            binding.idText.text = "Id: " + user.Id.toString()

            binding.deleteUser.setOnClickListener {
                sqliteViewModel.deleteUser(user)
                Log.d("pre" , "delete" )
            }
            binding.editUser.setOnClickListener {
                Log.d("pre" , "edit" )
                val dialogBox = DialogBox(context , user)
                dialogBox.show(supportFragmentManager, "t")
            }
        }
    }
}

class DiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItemPosition: User, newItemPosition: User): Boolean {
        return oldItemPosition.Id == newItemPosition.Id
    }

    override fun areContentsTheSame(oldItemPosition: User, newItemPosition: User): Boolean {
        return oldItemPosition==newItemPosition
    }

}
