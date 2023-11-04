package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentResultsBinding


class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ResultsViewModel =
            ViewModelProvider(this).get(ResultsViewModel::class.java)

        _binding = FragmentResultsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        ResultsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //Results Recycler View
        val movieShowRecyclerList: ArrayList<ResultsCard> = ArrayList()
        for (showMovie in ShowMovieList) {
            movieShowRecyclerList.add (
                ResultsCard(
                    showMovie.tconst,
                    showMovie.primaryTitle,
                    showMovie.titleType,
                    showMovie.startYear,
                    showMovie.genre,
                    showMovie.averageRating
                )
            )
        }

        //Move data into adapter
     //   mRecyclerView = binding.recyclerViewMoviesShows
   //     mRecylerView = setHasFixedSize(true)
   //     mRecyclerView.ResultsAdapter(movieShowRecyclerList,  this)
        return root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

