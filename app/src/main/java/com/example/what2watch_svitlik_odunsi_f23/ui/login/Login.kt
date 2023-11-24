package com.example.what2watch_svitlik_odunsi_f23.ui.login

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.ActivityLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class login : AppCompatActivity() {

    private val db = Firebase.firestore

    private lateinit var _binding: ActivityLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = ActivityLoginBinding.inflate(inflater, container, false)
        val root : View = binding.root

        val btnLogIn = binding.btnLogin


        binding.btnLogin.setOnClickListener {
            Log.d(TAG, "LogIn button was pressed!")
            findNavController().navigate(R.id.navigation_home)
            startActivity(Intent)
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}