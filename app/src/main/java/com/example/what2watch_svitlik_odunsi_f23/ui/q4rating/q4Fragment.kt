package com.example.what2watch_svitlik_odunsi_f23.ui.q4rating


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
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ4Binding
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.AnswersData
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.answersList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.quizFilters

class q4Fragment : Fragment() {

    val TAG = "SvitlikOdunsi"
    private var _binding: FragmentQ4Binding? = null
    private val binding get() = _binding!!
    val q1 = answersList[0].q1
    val q2 = answersList[0].q2
    val q3 = answersList[0].q3


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val q4ViewModel =
            ViewModelProvider(this).get(q4ViewModel::class.java)

        _binding = FragmentQ4Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val slider = binding.slider

        val textView: TextView = binding.textQ4
        q4ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val btnNext = binding.btnNext
        var finalSliderValue: Int = 0 // variable to store the final value

        btnNext.setOnClickListener {
            Log.d(TAG, "Next button was pressed!")
            //       answersList.add(AnswersData(q4 = finalSliderValue))
            //Log.d(TAG, "answersList size: ${answersList.size}")
            Log.d(TAG, "slider final value $finalSliderValue")
            answersList.add(0, AnswersData(q1, q2, q3, finalSliderValue))
            val answersData = AnswersData(answersList.toString())
            quizFilters(answersData)
            Log.d(TAG, "Everything uploaded into the array list")
            findNavController().navigate(R.id.navigation_quizresults)
        }

        slider.addOnChangeListener { _slider, value, fromUser ->
            finalSliderValue = value.toInt()
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

