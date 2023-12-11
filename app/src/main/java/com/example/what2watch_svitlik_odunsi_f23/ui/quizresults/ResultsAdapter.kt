package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.R
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.RecyclerResultsCard
import com.google.firebase.firestore.FirebaseFirestore


class ResultsAdapter(
    private val mExampleList: ArrayList<RecyclerResultsCard>, // takes in a list
    private val context: ResultsFragment, //pass in the context of results fragment to link data from one to another
    private val db: FirebaseFirestore = Firebase.firestore

) : RecyclerView.Adapter<ResultsAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder( //where you generate view for recycler view
        parent: ViewGroup,
        viewType: Int
    ): ExampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.movies_shows_card, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val (imageResource, name, type, genre, year, criticRating, userRating) = mExampleList[position]
        //  holder.mImageView.setImageResource(imageResource)
        holder.mTextView1.text = name
        holder.mTextView2.text = type.capitalize()
        holder.mTextGenre.text = genre.toString().capitalize()
        holder.mTextYear.text = year.toString()
        holder.mTextRating.text = criticRating.toString()
        holder.mStarRating.rating = userRating.toFloat()

        val userRatingBar = holder.itemView.findViewById<RatingBar>(R.id.ratingBar)
        userRatingBar.rating = userRating.toFloat()

        val TAG = "SvitlikOdunsi"
        holder.mStarRating.setOnRatingBarChangeListener { _, rating, _ ->
            Log.d(TAG, "Ratings bar touched: $rating")

            //I need to pull the current tconst into here and add to firebase
            //todo: add in username functionality
            //todo: add in something to make sure that the rating isn't being written to firebase more than once.
            //check if the rating is already in the database
            // if not, then run the code below
            // if it is, just do an update database query
            //todo: I need to add how to calculate the average user rating, so I would have to
            // loop through all user ratings for one movie, add that, and then divide that by total users

            val username: String = "need to add this"
            val ratingsCollection = Firebase.firestore.collection("MoviesReviews")
            val tConst = mExampleList[position].tconst

            val TAG = "SvitlikOdunsi"
            holder.mStarRating.setOnRatingBarChangeListener { _, rating, _ ->
                Log.d(TAG, "Ratings bar touched: $rating")

                // Check if the rating already exists in the database
                ratingsCollection
                    .whereEqualTo("tconst", tConst)
                    .whereEqualTo("username", username)
                    .get()
                    .addOnSuccessListener { documents ->
                        if (documents.isEmpty) {
                            // The rating is not in the database, add a new rating
                            val newUserReview = hashMapOf(
                                "tconst" to tConst,
                                "rating" to rating,
                                "username" to username
                            )

                            ratingsCollection.add(newUserReview)
                                .addOnSuccessListener { documentReference ->
                                    Log.d(TAG, "Rating added with ID: ${documentReference.id}")
                                }
                                .addOnFailureListener { e ->
                                    Log.e(TAG, "Error adding rating", e)
                                }
                        } else {
                            // The rating already exists, you might want to handle this case
                            Log.d(
                                TAG,
                                "Rating already exists for user $username and tconst $tConst"
                            )
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "Error checking if rating exists", e)
                    }

                // Update the userRating field in the MoviesAndShows document
                db.collection("MoviesAndShows")
                    .whereEqualTo("tconst", tConst)
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            val documentId = document.id
                            db.collection("MoviesAndShows").document(documentId)
                                .update("userRating", rating)
                                .addOnSuccessListener {
                                    Log.d(TAG, "Document successfully updated!")
                                }
                                .addOnFailureListener { e ->
                                    Log.e(TAG, "Error updating document", e)
                                }
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.e(TAG, "Error getting MoviesAndShows document", e)
                    }
            }
        }
    }

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.image_view)
        val mTextView1: TextView = itemView.findViewById(R.id.text_name)
        val mTextView2: TextView = itemView.findViewById(R.id.text_type1)
        val mTextGenre: TextView = itemView.findViewById(R.id.text_genre)
        val mTextYear: TextView = itemView.findViewById(R.id.text_year)
        val mTextRating: TextView = itemView.findViewById(R.id.text_rating)
        val mStarRating: RatingBar = itemView.findViewById(R.id.ratingBar)
    }


}
