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
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.MoviesAndShowsList
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.RecyclerResultsCard
import com.example.what2watch_svitlik_odunsi_f23.ui.shuffle.ShuffleRecyclerAdapter

class BrowseFragment : Fragment() {

    private var _binding: FragmentBrowseBinding? = null
    private lateinit var mRecyclerView: RecyclerView

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val TAG = "SvitlikOdunsi"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val browseViewModel =
            ViewModelProvider(this).get(BrowseViewModel::class.java)

 //       val btnFilterGenre = binding.btnHamburgerIcon

     //   btnFilterGenre.setOnClickListener {
            //When pressed, what shows up ?
            // Need to put a pop up that would show the different genres you can pick from
            // Then need to figure out how to change the recycler view based on that filter alone
      //  }

        val browseRecyclerList: ArrayList<RecyclerResultsCard> = ArrayList()
        for (result in MoviesAndShowsList) {
            browseRecyclerList.add(
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
        mRecyclerView.adapter = BrowseRecyclerAdapter(browseRecyclerList, this)

        _binding = FragmentBrowseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBrowse
        browseViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}