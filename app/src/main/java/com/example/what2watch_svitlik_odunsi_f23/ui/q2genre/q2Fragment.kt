package com.example.what2watch_svitlik_odunsi_f23.ui.q2genre

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ2Binding

class q2Fragment : Fragment() {

    private var _binding: FragmentQ2Binding? = null
    private lateinit var btnShow: Button
    private lateinit var btnMovie: Button


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val q2ViewModel =
            ViewModelProvider(this).get(q2ViewModel::class.java)

        _binding = FragmentQ2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textQ2
        q2ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val btnNext = binding.btnNext

        // Button click listeners
        btnNext.setOnClickListener {
            Log.d(TAG, "Next button was pressed!")
            findNavController().navigate(R.id.navigation_q3)
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}