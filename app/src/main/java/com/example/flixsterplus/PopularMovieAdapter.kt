package com.example.flixsterplus

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.init
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.RoundedCornersTransformation


const val POPULAR_MOVIE_EXTRA = "POPULAR_MOVIE_EXTRA"
private const val TAG = "PopularMoviesAdapter"
class PopularMovieAdapter(private val context: Context, private val popularMovies: List<PopularMovie>) :
    RecyclerView.Adapter<PopularMovieAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieAdapter.ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.popular_movies_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: PopularMovieAdapter.ViewHolder, position: Int) {
        val movie = popularMovies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return popularMovies.size
    }
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private var poster: ImageView = itemView.findViewById(R.id.pop_poster_movie)
        var title: TextView = itemView.findViewById(R.id.pop_title_movie)

        init {
            itemView.setOnClickListener(this)
        }
        override fun onClick(p0: View?) {
            val movie = popularMovies[absoluteAdapterPosition]
            val intent = Intent(context, MoviePopularityDetailsActivity::class.java)
            intent.putExtra(POPULAR_MOVIE_EXTRA, movie)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(context as Activity, (poster as View?)!!, "poster")
            context.startActivity(intent, options.toBundle())
        }
        fun bind(movie: PopularMovie) {
            title.text = movie.title
            val radius = 40; // corner radius, higher value = more rounded
            val margin = 10;
            Glide.with(itemView)
                .load("https://image.tmdb.org/t/p/w500${movie.poster}")
                .centerInside()
                .apply(RequestOptions.bitmapTransform(RoundedCornersTransformation(radius,margin)))
                .into(poster)

        }
    }
}


