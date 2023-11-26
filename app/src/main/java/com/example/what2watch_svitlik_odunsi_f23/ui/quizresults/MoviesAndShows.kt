package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

data class MoviesAndShows(
    //from imbd basics data collection
    val tconst: String ="",
    val titleType: String = "",    //this is how IMBD differentiates a show or a movie
    val primaryTitle: String = "",
    val startYear: Long = 0,
    val genre: String,
    val isAdult: Long = 0,

    //from imbd ratings data collection
    val averageRating: String = ""
)
var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf()

fun initializeMoviesAndShowsList(answersData: AnswersData) {
    val TAG = "SvitlikOdunsi"
    val moviesShow = FirebaseFirestore.getInstance().collection("MoviesAndShows")
    var query: Query = moviesShow

    answersData.q1?.let {
        query = query.whereEqualTo("titleType", it)
        Log.d(TAG, "Filter added to query: titleType = $it")
    }
    Log.d(TAG, "about to do firebase query")

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

   /* answersData.q2?.let {
        query = query.whereEqualTo("genre", it)
        Log.d(TAG, "Filter added to query: genre = $it")
    }

    answersData.q3?.let {
        query = query.whereEqualTo("startYear", it)
        Log.d(TAG, "Filter added to query: startYear = $it")
    }
*/

}
