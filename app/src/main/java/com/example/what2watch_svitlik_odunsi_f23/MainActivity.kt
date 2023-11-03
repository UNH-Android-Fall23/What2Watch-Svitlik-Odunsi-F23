package com.example.what2watch_svitlik_odunsi_f23

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.what2watch_svitlik_odunsi_f23.databinding.ActivityMainBinding
import com.example.what2watch_svitlik_odunsi_f23.ui.q1showmovie.ShowMovieData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_browse, R.id.navigation_shuffle, R.id.navigation_account,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //Hard coded data for testing
        val movie1 = ShowMovieData (
            "",
            "Movie",
            "Jurrasic Park",
            1993,
            arrayOf("Action", "Thriller", "Fantasy") ,
            1,
            90
        )

        val movie2 = ShowMovieData (
            "",
            "Movie",
            "Barbie",
            2023,
            arrayOf("Adventure"),
            1,
            70
        )

        val movie3 = ShowMovieData (
            "",
            "Movie",
            "How the Grinch Stole Christmas",
            2000,
            arrayOf("Comedy", "Fantasy"),
            0,
            63
        )

        val show1 = ShowMovieData (
            "",
            "TV Series",
            "Love it or List it",
            2008,
            arrayOf("Reality"),
            1,
            65
        )

        val show2 = ShowMovieData (
            "",
            "TV Series",
            "Competition",
            2011,
            arrayOf("Reality"),
            1,
            65
        )
    }



}