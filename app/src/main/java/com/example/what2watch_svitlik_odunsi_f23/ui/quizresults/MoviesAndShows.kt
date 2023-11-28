package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
var db = Firebase.firestore
/*
var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf( //TODO: This is temporary data for debugging recycler results view
    // TODO: This is temporary data, I will take this out soon
    MoviesAndShows ("dfsdf", "Movie", "Jurrasic Park", "Jurrasic Park", 1993, "Action", "1", "90", "1993", "9")
) */

fun initializeMoviesAndShowsList(answersData: AnswersData) {
    val TAG = "SvitlikOdunsi"
    Log.d(TAG, "Filter about to be query: = ${answersList[0].q1}, ${answersList[1].q1}")

    db.collection ("MoviesAndShows")
        .whereEqualTo("titleType", answersList[0].q1)
        .whereEqualTo("genre",answersList[1].q1)
        .get()
        .addOnSuccessListener {documents ->
            for (document in documents) {
                Log.d (TAG, "${document.id} => $document.data}")
                //I Need to take the document that was just grabbed and immediately enter it into our MoviesAndShowsList
                val movieOrShow = MoviesAndShows(
                    tconst = document.id,
                    titleType = document.getString("titleType") ?: "",
                    primaryTitle = document.getString("primaryTitle") ?: "",
                    originalTitle = document.getString("originalTitle") ?: "",
                    startYear = document.getLong("startYear") ?: 0,
                    genre = document.getString("genre") ?: "",
                    isAdult = document.getString("isAdult") ?: "",
                    runtime = document.getString("runtime") ?: "",
                    endYear = document.getString("endYear") ?: "",
                    averageRating = document.getString("averageRating") ?: ""
                )

                MoviesAndShowsList.add(movieOrShow)

                Log.d(TAG, "Added IMDb information into the MoviesAndShows Data array, ${(movieOrShow)}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents: ", exception)
        }
}//This says it is adding the imdb information into movieandshows list. but, it should be showing up in my results recycler view



/*
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
} */