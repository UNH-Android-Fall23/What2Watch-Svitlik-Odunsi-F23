package com.example.what2watch_svitlik_odunsi_f23.ui.browse

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentBrowseBinding
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.MoviesAndShows
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.MoviesAndShowsList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.RecyclerResultsCard
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.answersList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.AnswersData
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.db

class BrowseFragment : Fragment() {

    val TAG = "SvitlikOdunsi"
    val action: String = "action"
    val adventure: String = "adventure"
    val drama: String = "drama"
    private var _binding: FragmentBrowseBinding? = null
    private lateinit var mRecyclerView: RecyclerView
    private val binding get() = _binding!!
    val q1: String = "   "
    val q3: String = "   "
    val q4 = 10

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val browseViewModel = ViewModelProvider(this).get(BrowseViewModel::class.java)

        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val autoCompleteTextView = binding.autoCompleteTxt
        val adapterItems = arrayOf("Action", "Adventure", "Drama")
        val adapter = createArrayAdapter(adapterItems)
        autoCompleteTextView.setAdapter(adapter)

        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            val selectedItem = parent.getItemAtPosition(position).toString()

            when (selectedItem) {
                "Action" -> {
                    Log.d(TAG, "Action genre was chosen")
                    Log.d(TAG, "Answer data ${(action)}")
                    val answersData = AnswersData("", "action", "", 10)

                    // Add the database query here
                    if (answersList.isNotEmpty()) {
                        Log.d(
                            TAG,
                            "Filter about to be query: = ${answersList[0].q1}, ${answersList[0].q2}, ${answersList[0].q3}, ${answersList[0].q4}"
                        )

                        db.collection("MoviesAndShows")
                            .whereEqualTo("genre", answersList[0].q2)
                            .get()
                            .addOnSuccessListener { documents ->
                                for (document in documents) {
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

                                    Log.d(
                                        TAG,
                                        "Added IMDb information into the MoviesAndShows Data array, ${(movieOrShow)}"
                                    )
                                }

                                // Populate the RecyclerView after getting the data
                                val browseRecyclerList: ArrayList<RecyclerResultsCard> = ArrayList()
                                for (result in MoviesAndShowsList) {
                                    browseRecyclerList.add(
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
                                mRecyclerView.adapter =
                                    BrowseRecyclerAdapter(browseRecyclerList, this@BrowseFragment)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error getting documents: ", exception)
                            }
                    } else {
                        Log.e(TAG, "answersList is empty")
                    }
                }
            }
        }

        val textView: TextView = binding.textView4
        browseViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun createArrayAdapter(items: Array<String>): ArrayAdapter<String> {
        return ArrayAdapter(
            requireContext(),
            android.R.layout.simple_dropdown_item_1line,
            items
        )
    }
}
