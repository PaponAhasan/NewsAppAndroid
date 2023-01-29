package com.example.newsapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {
    private val TAG = "MyActivity"
    private lateinit var mNewsAdapter: NewsListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.newsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        createNewsList()

        mNewsAdapter = NewsListAdapter(this)
        recyclerView.adapter = mNewsAdapter
    }

    private fun createNewsList(){
        val newsApiUrl = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json"

        // Request a json response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, newsApiUrl, null,
            { response ->
                // request successfully
                val newsJsonArray = response.getJSONArray("articles")
                val newsArray = ArrayList<News>()
                for (i in 0 until newsJsonArray.length()) {
                    val newsDataObject = newsJsonArray.getJSONObject(i)
                    val newsData = News(
                        newsDataObject.getString("author"),
                        newsDataObject.getString("title"),
                        newsDataObject.getString("description"),
                        newsDataObject.getString("url"),
                        newsDataObject.getString("urlToImage"),
                        newsDataObject.getString("publishedAt"),
                        newsDataObject.getString("content")
                    )
                    newsArray.add(newsData)
                }
               mNewsAdapter.notifyNewsData(newsArray)
            },
            {error ->

                // request failed
                Log.e(TAG, "Failed")
                //Log.d("failed request", it.localizedMessage)
            })

        // Add the request to the RequestQueue.
        NewsSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}