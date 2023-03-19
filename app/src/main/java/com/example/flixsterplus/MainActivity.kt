package com.example.flixsterplus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.example.flixsterplus.databinding.ActivityMainBinding
import okhttp3.Headers
import org.json.JSONException
import kotlinx.serialization.json.Json


fun createJson() = Json {
    isLenient = true
    ignoreUnknownKeys = true
    useAlternativeNames = false
}


private const val TAG = "MainActivity/"
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"
private const val ARTICLE_SEARCH_URL = "https://api.themoviedb.org/3/movie/popular?api_key=${API_KEY}"
class MainActivity : AppCompatActivity() {
    private var popularMovieList = mutableListOf<PopularMovie>()
    private lateinit var rvPopularMovie: RecyclerView
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        rvPopularMovie=findViewById<RecyclerView>(R.id.rv_upcoming_movie)
        val upcomingMovieAdapter =PopularMovieAdapter(this, popularMovieList)
        rvPopularMovie.adapter = upcomingMovieAdapter

        rvPopularMovie.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val client = AsyncHttpClient()

        client.get(ARTICLE_SEARCH_URL, object : JsonHttpResponseHandler() {
            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch articles: $response")
            }

            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.i(TAG, "Successfully fetched articles: $json")
                try {
                    val parsedJson = createJson().decodeFromString(
                        SearchNewsResponse.serializer(),
                        json.jsonObject.toString()
                    )
                    parsedJson.result?.let { list ->
                        popularMovieList.addAll(list)
                    }
                    upcomingMovieAdapter.notifyDataSetChanged()

                } catch (e: JSONException) {
                    Log.e(TAG, "Exception: $e")
                }
            }

        })
    }
}