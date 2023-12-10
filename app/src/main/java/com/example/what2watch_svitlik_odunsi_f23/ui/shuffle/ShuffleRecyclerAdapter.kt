package com.example.what2watch_svitlik_odunsi_f23.ui.shuffle

import com.example.what2watch_svitlik_odunsi_f23.ui.quizresults.RecyclerResultsCard
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


class ShuffleRecyclerAdapter (
    private val mExampleList: ArrayList<RecyclerResultsCard>, // takes in a list
    private val context: ShuffleFragment //pass in the context of results fragment to link data from one to another
) : RecyclerView.Adapter<ShuffleRecyclerAdapter.ExampleViewHolder>() {

    val TAG = "SvitlikOdunsi"

    override fun onCreateViewHolder( //where you generate view for recycler view
        parent: ViewGroup,
        viewType: Int
    ): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.movies_shows_card, parent, false)
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

        holder.mStarRating.setOnRatingBarChangeListener { _, rating, _ ->
            Log.d(TAG, "Ratings bar touched: $rating")

            //hard coded data for firebase
            val tconst: String = "hi"
            val username: String = "poop"
            val ratingsCollection = Firebase.firestore.collection("MoviesReviews")

            val newUserReview = hashMapOf(
                "tconst" to tconst,
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
