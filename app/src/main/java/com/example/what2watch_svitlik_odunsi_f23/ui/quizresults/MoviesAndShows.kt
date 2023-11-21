package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

data class MoviesAndShows(
    //from imbd basics data collection
    val tconst: String ="",
    val titleType: String = "",    //this is how IMBD differentiates a show or a movie
    val primaryTitle: String = "",
    val startYear: Long = 0,
    val genre: String,
    val isAdult: Long = 0,

    //from imbd ratings data collection
    val averageRating: Long = 0
)

    var MoviesAndShowsList: ArrayList<MoviesAndShows> = arrayListOf( //TODO: This is temporary data for debugging recycler results view
        // TODO: This is temporary data, I will take this out soon
            MoviesAndShows ("dfsdf", "Movie", "Jurrasic Park", 1993, "Action", 1, 90),
            MoviesAndShows ("dfdsa", "Movie", "Barbie", 2023,"Adventure", 1, 70),
            MoviesAndShows ("kuybg", "Movie", "How the Grinch Stole Christmas", 2000,  "Comedy",   0, 63),
            MoviesAndShows ("otgl", "TV Series", "Love it or List It", 2008, "Reality",   1, 65),
            MoviesAndShows ("erwe", "TV Series", "The Voice", 2011,  "Reality",   1, 65),
    )
