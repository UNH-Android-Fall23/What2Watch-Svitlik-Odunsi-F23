package com.example.what2watch_svitlik_odunsi_f23.ui.shuffle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentShuffleBinding
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.MoviesAndShowsList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.RecyclerResultsCard

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
                result.averageRating)
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