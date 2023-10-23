package com.example.what2watch_svitlik_odunsi_f23.ui.q4rating

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ4Binding

class Q2Fragment : Fragment() {

    private var _binding: FragmentQ4Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val q4ViewModel =
            ViewModelProvider(this).get(q4ViewModel::class.java)

        _binding = FragmentQ4Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textQ4
        q4ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}

