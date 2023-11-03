package com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie

data class ShowMovieData(

    //from imbd basics data collection
    val titleType: String = "",    //this is how IMBD differentiates a show or a movie
    val primaryTitle: String = "",
    val startYear: Long = 0,
    val genre: String = "",
    val isAdult: Long = 0,

    //from imbd ratings data collection
    val averageRating: Long = 0
)

    var ShowMovieList: ArrayList<ShowMovieData> = arrayListOf()