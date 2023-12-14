package com.example.what2watch_svitlik_odunsi_f23.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment() {

    private val TAG = "SvitlikOdunsi"
    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = Firebase.auth
        db = Firebase.firestore

        val btnLogIn = binding.btnLogin
        val btnNewUser = binding.btnNewUser

        binding.btnLogin.setOnClickListener {
            Log.d(TAG, "LogIn button was pressed!")

            val email = binding.editTextTextEmailAddress2.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(activity, "Please enter your email and password", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        findNavController().navigate(R.id.navigation_home)
                    } else {
                        Toast.makeText(
                            activity,
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }

        binding.btnNewUser.setOnClickListener {
            Log.d(TAG, "New user button was pressed!")
            findNavController().navigate(R.id.navigation_home)
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}