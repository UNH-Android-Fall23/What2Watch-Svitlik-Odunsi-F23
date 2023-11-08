package com.example.what2watch_svitlik_odunsi_f23.ui.q4rating


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ4Binding
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.AnswersData
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.answersList


class q4Fragment : Fragment() {


    private var _binding: FragmentQ4Binding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val q4ViewModel =
            ViewModelProvider(this).get(q4ViewModel::class.java)


        //val rangeSlider = binding.rangeSlider2


        _binding = FragmentQ4Binding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textQ4
        q4ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        val btnSkip = binding.btnSkip
        val btnNext = binding.btnNext


        btnSkip.setOnClickListener {
            Log.d(TAG, "Skip button was pressed!")
            findNavController().navigate(R.id.navigation_quizresults)
        }


        btnNext.setOnClickListener {
            Log.d(TAG, "Next button was pressed!")
            findNavController().navigate(R.id.navigation_quizresults)
        }


        /*
        rangeSlider.addOnChangeListener { rangeSlider, value, fromUser ->
            val rating = value.toString()
            answersList.add(AnswersData(rating))
        }

*/
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

