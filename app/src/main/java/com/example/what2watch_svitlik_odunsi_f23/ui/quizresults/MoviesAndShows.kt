package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

data class MoviesAndShows(
    //from imbd basics data collection
    val tconst: String ="",
    val titleType: String = "",    //this is how IMBD differentiates a show or a movie
    val primaryTitle: String = "",
    val originalTitle: String ="",
    val startYear: Long = 0,
    val genre: String = "",
    val isAdult: String = "",
    val runtime: String = "",
    val endYear: String = "",
    val averageRating: String = "",

)
var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf()
/*
var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf( //TODO: This is temporary data for debugging recycler results view
    // TODO: This is temporary data, I will take this out soon
    MoviesAndShows ("dfsdf", "Movie", "Jurrasic Park", "Jurrasic Park", 1993, "Action", "1", "90", "1993", "9")
*/

fun initializeMoviesAndShowsList(answersData: AnswersData) {
    val TAG = "SvitlikOdunsi"
    val moviesShow = FirebaseFirestore.getInstance().collection("MoviesAndShows")
    var query: Query = moviesShow

    answersData.q1.let {
        val titleTypeValue = answersData.q1
        query = query.whereEqualTo("titleType", titleTypeValue)
        Log.d(TAG, "Filter added to query: = $titleTypeValue")
    }

    /*
     answersData.q2?.let {
         query = query.whereEqualTo("genre", it)
         Log.d(TAG, "Filter added to query: genre = $it")
     }

     answersData.q3?.let {
         query = query.whereEqualTo("startYear", it)
         Log.d(TAG, "Filter added to query: startYear = $it")
     }

      answersData.q4?.let {
         query = query.whereLessThanOrEqualTo("averageRating", it)
         Log.d(TAG, "Filter added to query: startYear = $it")
     }
 */
    query.get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val movieOrShow = document.toObject(MoviesAndShows::class.java)
                MoviesAndShowsList.add(movieOrShow)
                Log.d(TAG, "Retrieved: $movieOrShow")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
}