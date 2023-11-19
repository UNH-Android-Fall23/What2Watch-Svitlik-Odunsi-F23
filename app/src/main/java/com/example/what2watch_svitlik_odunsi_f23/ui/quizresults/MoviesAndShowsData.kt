package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

data class MoviesAndShowsData(
    //from imbd basics data collection
    var tconst: String ="",
    var titleType: String = "",    //this is how IMBD differentiates a show or a movie
    var primaryTitle: String = "",
    var startYear: Long = 0,
    var genre: Array <String> = emptyArray(),
    var isAdult: Long = 0,

    //from imbd ratings data collection
    val averageRating: Long = 0
)

    var ResultsList: ArrayList<MoviesAndShowsData> = arrayListOf( //TODO: This is temporary data for debugging recycler results view
        // TODO: This is temporary data, I will take this out soon
            MoviesAndShowsData ("dfsdf", "Movie", "Jurrasic Park", 1993, arrayOf("Action"), 1, 90),
            MoviesAndShowsData ("dfdsa", "Movie", "Barbie", 2023, arrayOf("Adventure"), 1, 70),
            MoviesAndShowsData ("kuybg", "Movie", "How the Grinch Stole Christmas", 2000, arrayOf("Comedy", "Fantasy"), 0, 63),
            MoviesAndShowsData ("otgl", "TV Series", "Love it or List It", 2008, arrayOf("Reality", "Competition"), 1, 65),
            MoviesAndShowsData ("erwe", "TV Series", "The Voice", 2011, arrayOf("Reality", "Competition"), 1, 65),
    )
