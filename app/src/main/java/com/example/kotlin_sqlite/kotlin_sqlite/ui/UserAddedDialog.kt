package com.example.kotlin_sqlite.kotlin_sqlite.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kotlin_sqlite.databinding.SuccessfulBinding

class UserAddedDialog() : DialogFragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: SuccessfulBinding = SuccessfulBinding.inflate(LayoutInflater.from(context))
        return binding.root.rootView
    }
}