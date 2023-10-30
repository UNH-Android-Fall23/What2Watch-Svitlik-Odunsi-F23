package com.example.what2watch_svitlik_odunsi_f23.ui.q2genre

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
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

        val btnAdventure = binding.btnAdventure
        val btnAction = binding.btnAction
        val btnSciFi = binding.btnScifi
        val btnRomance = binding.btnRomance
        val btnComedy = binding.btnComedy
        val btnThriller = binding.btnThriller
        val btnHorror = binding.btnHorror
        val btnRomCom = binding.btnRomcom
        val btnFaith = binding.btnFaith
        val btnDrama = binding.btnDrama
        val btnDocumentary = binding.btnDoc
        val btnFantasy = binding.btnFantasy
        val btnMystery = binding.btnMystery
        val btnHistory = binding.btnHistory

        btnAction.setOnClickListener {
            Log.d(TAG, "Action genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }

        btnAdventure.setOnClickListener {
            Log.d(TAG, "Adventure genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }

        btnSciFi.setOnClickListener {
            Log.d(TAG, "Sci Fi genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }

        btnRomance.setOnClickListener {
            Log.d(TAG, "Romance genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }

        btnMystery.setOnClickListener {
            Log.d(TAG, "Mystery genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }

        btnHistory.setOnClickListener {
            Log.d(TAG, "History genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }
        btnComedy.setOnClickListener {
            Log.d(TAG, "Comedy genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }
        btnThriller.setOnClickListener {
            Log.d(TAG, "Thriller genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }
        btnHorror.setOnClickListener {
            Log.d(TAG, "Horro genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }
        btnRomCom.setOnClickListener {
            Log.d(TAG, "Rom-com genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }

        btnFaith.setOnClickListener {
            Log.d(TAG, "Faith genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }
        btnDrama.setOnClickListener {
            Log.d(TAG, "Drama genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }
        btnDocumentary.setOnClickListener {
            Log.d(TAG, "Documentary genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }
        btnFantasy.setOnClickListener {
            Log.d(TAG, "Fantasy genre was chosen")
            findNavController().navigate(R.id.navigation_q3)
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}