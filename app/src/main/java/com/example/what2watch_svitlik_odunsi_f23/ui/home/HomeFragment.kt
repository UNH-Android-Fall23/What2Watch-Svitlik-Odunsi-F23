package com.example.what2watch_svitlik_odunsi_f23.ui.home

import android.annotation.SuppressLint
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
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentHomeBinding
import com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie.q1Fragment
import com.example.what2watch_svitlik_odunsi_f23.ui.q2genre.q2Fragment
import com.example.what2watch_svitlik_odunsi_f23.ui.q3decade.q3Fragment
import com.example.what2watch_svitlik_odunsi_f23.ui.q4rating.q4Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private val TAG = "What2WatchAndroidF23Tag"
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val btnBegin = view.findViewById<Button>(R.id.btn_begin)
        val btnPick = view.findViewById<Button>(R.id.btn_pickForMe)

        btnBegin.setOnClickListener {
            Log.d(TAG, "Begin the quiz button was pressed!")
            findNavController().navigate(R.id.navigation_q1)

            // TODO: Get this to swipe to the next quiz fragment
        }

        btnPick.setOnClickListener {
            Log.d(TAG, "Just pick for me button was pressed!")
            // TODO: Get this to swipe to the shuffle fragment
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
