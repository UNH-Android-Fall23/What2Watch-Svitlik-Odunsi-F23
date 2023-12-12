package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.R
import com.example.what2watch_svitlik_odunsi_f23.databinding.FragmentResultsBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Suppress("NAME_SHADOWING")
class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val db = Firebase.firestore
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
         lifecycleScope.launch {
             Log.d(TAG, "About to show splash screen")
             showResultsplashScreen()
             delay(2000)
             Log.d(TAG, "About to create recycler data and move it into recycler adapter")
             createRecycler()
             mRecyclerView = binding.recyclerViewMoviesShows
             val resultsRecyclerList: ArrayList<RecyclerResultsCard> = createRecycler()
             mRecyclerView.setHasFixedSize(true)
             mRecyclerView.layoutManager = LinearLayoutManager(context)
             mRecyclerView.adapter = ResultsAdapter(resultsRecyclerList, this@ResultsFragment)
         }

    //goToResutls()

         return root
     }


     private fun showResultsplashScreen () {
         Log.d(TAG, "About to show it in the function: ")
         findNavController().navigate(R.id.navigation_recyclerSplashScreen)
         Log.d(TAG,"Splashscreen pulled up")
     }

    private suspend fun createRecycler(): ArrayList<RecyclerResultsCard> {
        val resultsRecyclerList: ArrayList<RecyclerResultsCard> = ArrayList()
        for (result in MoviesAndShowsList) {
            resultsRecyclerList.add(
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

        return resultsRecyclerList

    }

    private fun goToResutls (){
        findNavController().navigate(R.id.navigation_quizresults)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

