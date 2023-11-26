package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

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

    var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf( //TODO: This is temporary data for debugging recycler results view
        // TODO: This is temporary data, I will take this out soon
            MoviesAndShows ("dfsdf", "Movie", "Jurrasic Park", 1993, "Action", 1, "4"),
            MoviesAndShows ("dfdsa", "Movie", "Barbie", 2023,"Adventure", 1, "7"),
            MoviesAndShows ("kuybg", "Movie", "How the Grinch Stole Christmas", 2000,  "Comedy",   0, "63"),
            MoviesAndShows ("otgl", "TV Series", "Love it or List It", 2008, "Reality",   1, "65"),
            MoviesAndShows ("erwe", "TV Series", "The Voice", 2011,  "Reality",   1, "65"),
    )

/*
fun firebaseFilters(answersData: AnswersData, moviesAndShowsList: ArrayList<MoviesAndShows>) {
    val moviesShow = FirebaseFirestore.getInstance().collection("MoviesAndShows")
    var query: Query = moviesShow

    answersData.q1?.let {
        query = query.whereEqualTo("titleType", it)
    }

    answersData.q2?.let {
        query = query.whereEqualTo("genre", it)
    }

    answersData.q3?.let {
        query = query.whereEqualTo("startYear", it)
    }

    answersData.q4?.let {
        query = query.whereEqualTo("averageRating", it)
    }

    query.get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                val movieOrShow = document.toObject(MoviesAndShows::class.java)
                moviesAndShowsList.add(movieOrShow)
                Log.d("Firebase data retrieved", ": $movieOrShow")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(""Error getting documents: ", exception)
        }
} */