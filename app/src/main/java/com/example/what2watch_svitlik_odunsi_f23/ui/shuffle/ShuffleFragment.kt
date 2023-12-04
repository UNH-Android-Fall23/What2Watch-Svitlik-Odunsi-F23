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

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        val k = 10

        db.collection("MoviesAndShows")
            .get()
            .addOnSuccessListener { documents ->
                val shuffledDocuments = documents.toMutableList().shuffled()
                val selectedDocuments = shuffledDocuments.take(k)

                for (document in selectedDocuments) {
                    Log.d(TAG, "${document.id} => $document.data}")
                    //I Need to take the document that was just grabbed and immediately enter it into our MoviesAndShowsList
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
                        averageRating = document.getString("averageRating") ?: ""
                    )

                    MoviesAndShowsList.add(movieOrShow)

                    Log.d(TAG,
                        "Added IMDb information into the MoviesAndShows Data array, ${(movieOrShow)}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

        val textView: TextView = binding.textHome
        shuffleViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val shuffleRecyclerList: ArrayList<RecyclerResultsCard> = ArrayList()
        for (result in MoviesAndShowsList) {
            shuffleRecyclerList.add(
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