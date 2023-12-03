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
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.MoviesAndShowsList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.RecyclerResultsCard
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.answersList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.AnswersData
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.browseFilters

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
                    answersList.add(0, AnswersData("", "action", "", 10))

                }

                "Adventure" -> {
                    Log.d(TAG, "adventure genre was chosen")
                    Log.d(TAG, "Answer data ${(adventure)}")
                    answersList.add(0, AnswersData("", "adventure", "", 10))

                }

                "Drama" -> {
                    Log.d(TAG, "Drama genre was chosen")
                    Log.d(TAG, "Answer data ${(drama)}")
                    answersList.add(0, AnswersData(q1, drama, q3 , 10))

                }
            }

            val answersData = AnswersData(answersList.toString())
            browseFilters(answersData)
            Log.d(TAG, "Everything uploaded into the array list")

        }
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
        mRecyclerView.adapter = BrowseRecyclerAdapter(browseRecyclerList, this)

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
/* Have it add the quiz question into the array.. so genre is question 2  so add that intoa SECOND ARRAY and then do the qiery of that, or it may have to be 0, we shall see */
