package com.example.flixsterplus

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MoviePopularityDetailsActivity: AppCompatActivity() {
    private lateinit var movieImageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var popularityTextView: TextView
    private lateinit var overviewTextView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popular_movie_view)
        movieImageView=findViewById(R.id.pop_poster_movie)
        titleTextView=findViewById(R.id.pop_title_movie)
        dateTextView=findViewById(R.id.pop_release_date)
        popularityTextView=findViewById(R.id.popularity_movie)
        overviewTextView=findViewById(R.id.pop_overview_movie)
        val movie = intent.getSerializableExtra(POPULAR_MOVIE_EXTRA) as PopularMovie
        titleTextView.text = movie.title
        overviewTextView.text = movie.overview
        dateTextView.text = movie.releaseDate
        popularityTextView.text= "Popularity Score: " + movie.voteAvg.toString()
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${movie.poster}")
            .into(movieImageView)

    }
}