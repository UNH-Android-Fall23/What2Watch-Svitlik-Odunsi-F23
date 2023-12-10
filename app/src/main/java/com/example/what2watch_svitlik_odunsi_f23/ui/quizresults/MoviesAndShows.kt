package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

data class MoviesAndShows(
    val tconst: Comparable<*> = "",
    val titleType: String = "",    //this is how IMBD differentiates a show or a movie
    val primaryTitle: String = "",
    val originalTitle: String ="",
    val startYear: Long = 0,
    val genre: String = "",
    val isAdult: String = "",
    val runtime: String = "",
    val endYear: String = "",
    val criticRating: String = "",
    val userRating:Int  = 0

) {
}

var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf()
var db = Firebase.firestore
/*
var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf( //TODO: This is temporary data for debugging recycler results view
    // TODO: This is temporary data, I will take this out soon
    MoviesAndShows ("dfsdf", "Movie", "Jurrasic Park", "Jurrasic Park", 1993, "Action", "1", "90", "1993", "9")
) */

fun quizFilters(answersData: AnswersData) {
    val startYearFilter = answersList[0].q3.toLong()
    val endYearFilter = (startYearFilter + 9)


    val TAG = "SvitlikOdunsi"
    Log.d(TAG, "Filter about to be query: = ${answersList[0].q1}, ${answersList[0].q2}, ${answersList[0].q3}, ${answersList[0].q4}")

    db.collection ("MoviesAndShows")
        .whereEqualTo("titleType", answersList[0].q1)
        .whereEqualTo("genre",answersList[0].q2)
        .whereGreaterThanOrEqualTo("startYear", answersList[0].q3.toLong())
        .whereLessThan("startYear", endYearFilter)
        .whereEqualTo("averageRating", answersList[0].q4.toString())
        .get()
        .addOnSuccessListener {documents ->
            for (document in documents) {
                Log.d (TAG, "${document.id} => $document.data}")
                //I Need to take the document that was just grabbed and immediately enter it into our MoviesAndShowsList
                val movieOrShow = MoviesAndShows(
                    tconst = document.getString("tconst") ?: "",
                    titleType = document.getString("titleType") ?: "",
                    primaryTitle = document.getString("primaryTitle") ?: "",
                    originalTitle = document.getString("originalTitle") ?: "",
                    startYear = document.getLong("startYear") ?: 0,
                    genre = document.getString("genre") ?: "",
                    isAdult = document.getString("isAdult") ?: "",
                    runtime = document.getString("runtime") ?: "",
                    endYear = document.getString("endYear") ?: "",
                    criticRating = document.getString("averageRating") ?: ""
                )

                MoviesAndShowsList.add(movieOrShow)

                Log.d(TAG, "Added IMDb information into the MoviesAndShows Data array, ${(movieOrShow)}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
}
