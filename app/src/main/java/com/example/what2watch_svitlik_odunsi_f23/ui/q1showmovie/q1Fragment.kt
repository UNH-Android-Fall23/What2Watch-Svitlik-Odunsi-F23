package com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie

import android.content.ContentValues.TAG
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
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ1Binding


class q1Fragment : Fragment() {
    private val TAG = "What2WatchAndroidF23Tag"
    private var _binding: FragmentQ1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQ1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val btnShow = binding.btnShow
        val btnMovie = binding.btnMovie

            btnShow.setOnClickListener {
                Log.d(TAG, "Show button was pressed!")
                findNavController().navigate(R.id.navigation_q2)
            }

            btnMovie.setOnClickListener {
                Log.d(TAG, "Movie button was pressed!")
                findNavController().navigate(R.id.navigation_q2)
            }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
