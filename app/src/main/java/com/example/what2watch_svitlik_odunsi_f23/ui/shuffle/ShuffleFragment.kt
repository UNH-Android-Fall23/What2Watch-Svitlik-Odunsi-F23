package com.example.what2watch_svitlik_odunsi_f23.ui.shuffle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentShuffleBinding

class ShuffleFragment : Fragment() {

    private var _binding: FragmentShuffleBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val shuffleViewModel =
            ViewModelProvider(this).get(ShuffleViewModel::class.java)

        _binding = FragmentShuffleBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textShuffle
        shuffleViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}