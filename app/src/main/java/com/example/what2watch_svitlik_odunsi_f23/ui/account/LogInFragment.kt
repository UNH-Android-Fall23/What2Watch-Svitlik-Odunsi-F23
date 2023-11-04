package com.example.what2watch_svitlik_odunsi_f23.ui.account

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentAccountBinding
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentLoginBinding
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ1Binding
import com.google.android.material.tabs.TabLayout.TabGravity

class LogInFragment : Fragment() {

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

        val btnLogIn = binding.btnLogin

        btnLogIn.setOnClickListener {
            Log.d(TAG, "Show button was pressed!")
            findNavController().navigate(R.id.navigation_q1)
        }
        btnLogIn.setOnClickListener {
            Log.d(TAG, "Login Successful!")
            findNavController().navigate(R.id.navigation_q1)
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}



