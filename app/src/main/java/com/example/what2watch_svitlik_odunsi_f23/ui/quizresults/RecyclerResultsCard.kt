package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

data class RecyclerResultsCard(
    val tconst: String,
    val primaryTitle: String,
    val titleType: String,
    val startYear: Long,
    val genre: String,
    val averageRating: String,
    val userRating: Int
)