package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

data class ResultsData(
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

    var ResultsList: ArrayList<ResultsData> = arrayListOf( //TODO: This is temporary data for debugging recycler results view
        // TODO: This is temporary data, I will take this out soon
            ResultsData ("dfsdf", "Movie", "Jurrasic Park", 1993, arrayOf("Action"), 1, 90),
            ResultsData ("dfdsa", "Movie", "Barbie", 2023, arrayOf("Adventure"), 1, 70),
            ResultsData ("kuybg", "Movie", "How the Grinch Stole Christmas", 2000, arrayOf("Comedy", "Fantasy"), 0, 63),
            ResultsData ("otgl", "TV Series", "Love it or List It", 2008, arrayOf("Reality", "Competition"), 1, 65),
            ResultsData ("erwe", "TV Series", "The Voice", 2011, arrayOf("Reality", "Competition"), 1, 65),
    )
