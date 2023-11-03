package com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie

data class Shows (

    //from imbd basics data collection
    val primaryTitle: String,
    val startYear: Long,
    val genre: String,
    val reviewRating: String,

    //from imbd ratings data collection
    val averageRating: Long

)