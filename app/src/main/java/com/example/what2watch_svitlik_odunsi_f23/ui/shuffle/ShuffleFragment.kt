package com.example.what2watch_svitlik_odunsi_f23.ui.shuffle

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentShuffleBinding
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.MoviesAndShows
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.MoviesAndShowsList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.RecyclerResultsCard
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.db

class ShuffleFragment : Fragment() {

    private var _binding: FragmentShuffleBinding? = null
    private lateinit var mRecyclerView: RecyclerView
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

        db.collection("MoviesAndShows")
            .limit(25)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => $document.data}")
                    val movieOrShow = MoviesAndShows(
                        tconst = document.id,
                        titleType = document.getString("titleType") ?: "",
                        primaryTitle = document.getString("primaryTitle") ?: "",
                        originalTitle = document.getString("originalTitle") ?: "",
                        startYear = document.getLong("startYear") ?: 0,
                        genre = document.getString("genre") ?: "",
                        isAdult = document.getString("isAdult") ?: "",
                        runtime = document.getString("runtime") ?: "",
                        endYear = document.getString("endYear") ?: "",
                        criticRating = document.getString("averageRating") ?: ""
                    )

                    MoviesAndShowsList.add(movieOrShow)

                    Log.d(TAG,
                        "Added IMDb information into the MoviesAndShows Data array, ${(movieOrShow)}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        val textView: TextView = binding.textShuffle
        shuffleViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val shuffleRecyclerList: ArrayList<RecyclerResultsCard> = ArrayList()
        for (result in MoviesAndShowsList) {
            shuffleRecyclerList.add(
                RecyclerResultsCard(
                    result.tconst.toString(),
                    result.primaryTitle,
                    result.titleType,
                    result.startYear,
                    result.genre,
                    result.criticRating,
                    result.userRating

                )
            )
        }



        mRecyclerView = binding.recyclerViewMoviesShows
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = ShuffleRecyclerAdapter(shuffleRecyclerList, this)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

