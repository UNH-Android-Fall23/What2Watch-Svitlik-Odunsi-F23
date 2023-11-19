package com.example.what2watch_svitlik_odunsi_f23.ui.browse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentBrowseBinding
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.ResultsAdapter
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.ResultsCard
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.ResultsList

class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private lateinit var mRecyclerView: RecyclerView

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val browseViewModel = ViewModelProvider(this).get(BrowseViewModel::class.java)

        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBrowse
        browseViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        //Recycler view for each genre
        val actionRecyclerList: ArrayList<ResultsCard> = ArrayList()
        for (result in ResultsList) {
            actionRecyclerList.add(
                ResultsCard(
                    result.tconst,
                    result.primaryTitle,
                    result.titleType,
                    result.startYear,
                    result.genre,
                    result.averageRating
                )
            )
        }

        //Move data into adapter
        mRecyclerView = binding.recyclerViewMoviesShows
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mRecyclerView.adapter = ResultsAdapter(actionRecyclerList, this)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}