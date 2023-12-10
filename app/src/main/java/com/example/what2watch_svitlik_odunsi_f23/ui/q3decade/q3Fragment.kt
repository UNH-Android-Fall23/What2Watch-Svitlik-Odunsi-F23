package com.example.what2watch_svitlik_odunsi_f23.ui.q3decade


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
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentQ3Binding
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.AnswersData
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.answersList


class q3Fragment : Fragment() {


    private var _binding: FragmentQ3Binding? = null
    val TAG = "SvitlikOdunsi"


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val eighties = "1980"
    private val nineties = "1990"
    private val twothousands = "2000"
    private val twentytens = "2010"
    private val twentytwenies = "2020"
    private val recent = "2023"
    val q1 = answersList[0].q1
    val q2 = answersList[0].q2


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val q3ViewModel =
            ViewModelProvider(this).get(q3ViewModel::class.java)
        _binding = FragmentQ3Binding.inflate(inflater, container, false)
        val root: View = binding.root


        val textView: TextView = binding.textQ3
        q3ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Buttons for the different decades
        val btn80s = binding.btn80s
        val btn90s = binding.btn90s
        val btn00s = binding.btn00s
        val btn10s = binding.btn10s
        val btn20s = binding.btn20s
        val q1 = answersList[0].q1
        val q2 = answersList[0].q2



        btn80s.setOnClickListener {
            Log.d(TAG, "80s button was pressed!")
            // answersList.add(AnswersData(q3 = eighties))
            Log.d(TAG, "Answer data ${(eighties)}")
            answersList.add(0, AnswersData(q1, q2, eighties))
            findNavController().navigate(R.id.navigation_q4)
        }


        btn90s.setOnClickListener {
            Log.d(TAG, "90s button was pressed!")
            //           answersList.add(AnswersData(q3 = nineties))
            Log.d(TAG, "Answer data ${(nineties)}")
            answersList.add(0, AnswersData(q1, q2, nineties))
            findNavController().navigate(R.id.navigation_q4)
        }


        btn00s.setOnClickListener {
            Log.d(TAG, "00s button was pressed!")
            //           answersList.add(AnswersData(q3 = twothousands))
            Log.d(TAG, "Answer data ${(twothousands)}")
            answersList.add(0, AnswersData(q1, q2, twothousands))
            findNavController().navigate(R.id.navigation_q4)
        }


        btn10s.setOnClickListener {
            Log.d(TAG, "10s button was pressed!")
            //            answersList.add(AnswersData(q3 = twentytens))
            Log.d(TAG, "Answer data ${(twentytens)}")
            answersList.add(0, AnswersData(q1, q2, twentytens))
            findNavController().navigate(R.id.navigation_q4)
        }


        btn20s.setOnClickListener {
            Log.d(TAG, "20s button was pressed!")
            //answersList.add(AnswersData(q3 = twentytwenies))
            Log.d(TAG, "Answer data ${(twentytwenies)}")
            answersList.add(0, AnswersData(q1, q2, twentytwenies))
            Log.d(TAG, "answersList size: ${answersList.size}")
            findNavController().navigate(R.id.navigation_q4)
        }


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
