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
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.AnswersData
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.answersList


class q2Fragment : Fragment() {


    private var _binding: FragmentQ2Binding? = null
    private lateinit var btnShow: Button
    private lateinit var btnMovie: Button
    private val adventure = "Adventure"
    private val action = "action"
    private val scifi = "scifi"
    private val romance = "romance"
    private val comedy = "comedy"
    private val thriller = "thriller"
    private val horror = "horror"
    private val romcom = "romcom"
    private val faith = "faith"
    private val drama = "drama"
    private val documentary = "documentary"
    private val fantasy = "fantasy"
    private val mystery = "mystery"
    private val history = "history"
    val TAG = "SvitlikOdunsi"

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


        val textView: TextView = binding.textGenre
        q2ViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        //Buttons for all different movie genre
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
       //     answersList.add(AnswersData(q2 = action))
            findNavController().navigate(R.id.navigation_q3)
        }


        btnAdventure.setOnClickListener {
            Log.d(TAG, "Adventure genre was chosen")
            //       answersList.add(AnswersData(q2 = adventure))
            findNavController().navigate(R.id.navigation_q3)
        }


        btnSciFi.setOnClickListener {
            Log.d(TAG, "Sci Fi genre was chosen")
            //           answersList.add(AnswersData(q2 = scifi))
            Log.d(TAG, "answersList size: ${answersList.size}")
            findNavController().navigate(R.id.navigation_q3)
        }


        btnRomance.setOnClickListener {
            Log.d(TAG, "Romance genre was chosen")
            //         answersList.add(AnswersData(q2 = romance))
            findNavController().navigate(R.id.navigation_q3)
        }


        btnMystery.setOnClickListener {
            Log.d(TAG, "Mystery genre was chosen")
            //          answersList.add(AnswersData(q2 = mystery))
            findNavController().navigate(R.id.navigation_q3)
        }


        btnHistory.setOnClickListener {
            Log.d(TAG, "History genre was chosen")
            //        answersList.add(AnswersData(q2 = history))
            findNavController().navigate(R.id.navigation_q3)
        }
        btnComedy.setOnClickListener {
            Log.d(TAG, "Comedy genre was chosen")
            //        answersList.add(AnswersData(q2 = comedy))
            findNavController().navigate(R.id.navigation_q3)
        }
        btnThriller.setOnClickListener {
            Log.d(TAG, "Thriller genre was chosen")
            //         answersList.add(AnswersData(q2 = thriller))
            findNavController().navigate(R.id.navigation_q3)
        }
        btnHorror.setOnClickListener {
            Log.d(TAG, "Horror genre was chosen")
            //   answersList.add(AnswersData(q2 = horror))
            findNavController().navigate(R.id.navigation_q3)
        }
        btnRomCom.setOnClickListener {
            Log.d(TAG, "Rom-com genre was chosen")
            //       answersList.add(AnswersData(q2 = romcom))
            findNavController().navigate(R.id.navigation_q3)
        }


        btnFaith.setOnClickListener {
            Log.d(TAG, "Faith genre was chosen")
            //      answersList.add(AnswersData(q2 = faith))
            findNavController().navigate(R.id.navigation_q3)
        }
        btnDrama.setOnClickListener {
            Log.d(TAG, "Drama genre was chosen")
            //      answersList.add(AnswersData(q2 = drama))
            findNavController().navigate(R.id.navigation_q3)
        }
        btnDocumentary.setOnClickListener {
            Log.d(TAG, "Documentary genre was chosen")
            //    answersList.add(AnswersData(q2 = documentary))
            findNavController().navigate(R.id.navigation_q3)
        }
        btnFantasy.setOnClickListener {
            Log.d(TAG, "Fantasy genre was chosen")
            //    answersList.add(AnswersData(q2 = fantasy))
            findNavController().navigate(R.id.navigation_q3)
        }


        return root


    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
