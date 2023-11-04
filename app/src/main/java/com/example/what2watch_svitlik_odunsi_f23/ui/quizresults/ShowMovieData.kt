package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

data class ShowMovieData(

    //from imbd basics data collection
    val tconst: String ="",
    val titleType: String = "",    //this is how IMBD differentiates a show or a movie
    val primaryTitle: String = "",
    val startYear: Long = 0,
    val genre: Array <String> = emptyArray(),
    val isAdult: Long = 0,

    //from imbd ratings data collection
    val averageRating: Long = 0
)

    var ShowMovieList: ArrayList<ShowMovieData> = arrayListOf(
        // TODO: This is temporary data, I will take this out soon
            ShowMovieData ("dfsdf", "Movie", "Jurrasic Park", 1993, arrayOf("Action"), 1, 90),
            ShowMovieData ("dfdsa", "Movie", "Barbie", 2023, arrayOf("Adventure"), 1, 70),
            ShowMovieData ("kuybg", "Movie", "How the Grinch Stole Christmas", 2000, arrayOf("Comedy", "Fantasy"), 0, 63),
            ShowMovieData ("otgl", "TV Series", "Love it or List It", 2008, arrayOf("Reality", "Competition"), 1, 65),
            ShowMovieData ("erwe", "TV Series", "The Voice", 2011, arrayOf("Reality", "Competition"), 1, 65),
    )
