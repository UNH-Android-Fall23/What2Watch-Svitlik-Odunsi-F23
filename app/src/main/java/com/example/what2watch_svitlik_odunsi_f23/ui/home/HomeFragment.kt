package com.example.what2watch_svitlik_odunsi_f23.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private val TAG = "What2WatchAndroidF23Tag"
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Use the binding to access views
        val btnBegin = binding.btnBegin
        val btnPick = binding.btnPickForMe

        // Button click listeners
        btnBegin.setOnClickListener {
            Log.d(TAG, "Begin the quiz button was pressed!")
            findNavController().navigate(R.id.navigation_q1)
        }

        btnPick.setOnClickListener {
            Log.d(TAG, "Just pick for me button was pressed!")
            findNavController().navigate(R.id.navigation_shuffle)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
