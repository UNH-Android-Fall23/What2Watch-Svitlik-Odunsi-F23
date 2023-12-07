package com.example.what2watch_svitlik_odunsi_f23.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentLoginBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LogInFragment : Fragment() {

    private val db = Firebase.firestore
    val TAG = "SvitlikOdunsi"

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
        val root : View = binding.root

        val btnLogIn = binding.btnLogin
        val btnNewUser = binding.btnNewUser


        binding.btnLogin.setOnClickListener {
            Log.d(TAG, "LogIn button was pressed!")
            findNavController().navigate(R.id.navigation_home)
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



