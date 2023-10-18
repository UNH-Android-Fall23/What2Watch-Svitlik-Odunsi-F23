package com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ1Binding

class BrowseFragment : Fragment() {

    private var _binding: FragmentQ1Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val q1ViewModel =
            ViewModelProvider(this).get(q1ViewModel::class.java)

        _binding = FragmentQ1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textQ1
        q1ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}