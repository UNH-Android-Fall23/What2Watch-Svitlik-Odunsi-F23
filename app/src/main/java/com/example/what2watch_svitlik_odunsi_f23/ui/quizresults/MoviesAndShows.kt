package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.content.ContentValues.TAG
import android.util.Log
import com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie.q1Fragment
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.AnswersData
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

fun initializeMoviesAndShowsList(answersData: AnswersData) {
    val TAG = "SvitlikOdunsi"
    val moviesShow = FirebaseFirestore.getInstance().collection("MoviesAndShows")
    var query: Query = moviesShow

    answersData.q1.let {
        val titleTypeValue = answersData.q1
        query = query.whereEqualTo("titleType", titleTypeValue)
        Log.d(TAG, "Filter added to query: titleType = $titleTypeValue")
    }

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