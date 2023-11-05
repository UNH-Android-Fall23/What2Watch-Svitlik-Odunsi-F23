package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

data class ResultsCard (
    val tconst: String,
    val primaryTitle: String,
    val titleType: String,
    val startYear: Long,
    val genre: Array <String>,
    val averageRating: Long = 0
)