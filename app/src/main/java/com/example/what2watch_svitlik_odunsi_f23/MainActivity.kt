package com.example.what2watch_svitlik_odunsi_f23

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RatingBar
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.what2watch_svitlik_odunsi_f23.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val TAG = "SvitlikOdunsi"

    private var db = Firebase.firestore


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
                R.id.navigation_home,
                R.id.navigation_browse,
                R.id.navigation_shuffle,
                R.id.navigation_profile,
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    fun onLogoutClick(view: View) {
        // Handle the logout action here.
    }

    fun handleRatingBarChange(rating: Int) {
        val ratingValue = rating
        Log.d(TAG, "Ratings bar touched: $rating")

        //hard coded data for firebase
        val tconst: String = "fsdfksdf"
        val username: String = "sarahsvitlik"
        val ratingsCollection = Firebase.firestore.collection("MovieReviews")

        val newUserReview = hashMapOf(
            "tconst" to tconst,
            "rating" to ratingValue,
            "username" to username
        )

        ratingsCollection.add(newUserReview)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Rating added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.e(TAG, "Error adding rating", e)
            }
    }
}