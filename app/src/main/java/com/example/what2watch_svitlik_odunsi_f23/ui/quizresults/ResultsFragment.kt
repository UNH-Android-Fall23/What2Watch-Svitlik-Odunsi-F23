package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentResultsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val db = Firebase.firestore
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var mRecyclerView: RecyclerView
    val TAG = "SvitlikOdunsi"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ResultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        ResultsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }


        //Results Recycler View
        val resultsRecyclerList: ArrayList<RecyclerResultsCard> = ArrayList()
        for (result in MoviesAndShowsList) {
            resultsRecyclerList.add(
                RecyclerResultsCard(
                    result.tconst,
                    result.primaryTitle,
                    result.titleType,
                    result.startYear,
                    result.genre,
                    result.averageRating)
                )
        }

        //Move data into adapter
        mRecyclerView = binding.recyclerViewMoviesShows
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = ResultsAdapter(resultsRecyclerList, this)

        return root

        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

