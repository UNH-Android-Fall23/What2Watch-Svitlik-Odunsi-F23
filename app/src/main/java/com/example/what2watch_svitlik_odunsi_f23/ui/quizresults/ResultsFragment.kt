package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.MainActivity
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentResultsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val db = Firebase.firestore
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var ratingBar: RatingBar
    private val binding get() = _binding!!
    private val TAG = "SvitlikOdunsi"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val resultsViewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        resultsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it


            ratingBar = root.findViewById(R.id.ratingBar)
            ratingBar.setOnRatingBarChangeListener { _, rating, _ ->
                Log.d(TAG, "RatingBarChangeListener triggered. Rating: $rating")
                val ratingValue = rating.toInt()
                (requireActivity() as MainActivity).handleRatingBarChange(ratingValue)
            }

        }


        // Results Recycler View
        val resultsRecyclerList: ArrayList<RecyclerResultsCard> = ArrayList()
        for (result in MoviesAndShowsList) {
            resultsRecyclerList.add(
                RecyclerResultsCard(
                    result.tconst,
                    result.primaryTitle,
                    result.titleType,
                    result.startYear,
                    result.genre,
                    result.averageRating
                )
            )
        }

        // Move data into adapter
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
