package com.example.what2watch_svitlik_odunsi_f23.ui.quizresults

import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.what2watch_svitlik_odunsi_f23.R


class ResultsAdapter (
    private val mExampleList: ArrayList<ResultsCard>, // takes in a list
    private val context: ResultsFragment //pass in the context of results fragment to link data from one to another
) : RecyclerView.Adapter<ResultsAdapter.ExampleViewHolder>() {
    override fun onCreateViewHolder( //where you generate view for recycler view
        parent: ViewGroup,
        viewType: Int
    ): ExampleViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.results_card, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val (imageResource, name, type, genre, year, rating) = mExampleList[position]
      //  holder.mImageView.setImageResource(imageResource)
        holder.mTextView1.text = name
        holder.mTextView2.text = type
        holder.mTextGenre.text = genre.toString()
        holder.mTextYear.text = year.toString()
        holder.mTextRating.text = rating.toString()


        holder.itemView.setOnClickListener {
            Log.d(TAG, "Clicked position i s$position")
        }
    }

    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mImageView: ImageView = itemView.findViewById(R.id.image_view)
        val mTextView1: TextView = itemView.findViewById(R.id.text_name)
        val mTextView2: TextView = itemView.findViewById(R.id.text_type1)
        val mTextGenre: TextView = itemView.findViewById(R.id.text_genre)
        val mTextYear: TextView = itemView.findViewById(R.id.text_year)
        val mTextRating: TextView = itemView.findViewById(R.id.text_rating)
    }

}

