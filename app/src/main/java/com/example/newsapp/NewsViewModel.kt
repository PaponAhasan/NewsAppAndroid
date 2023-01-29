package com.example.newsapp

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import androidx.lifecycle.ViewModelProvider
import com.android.volley.toolbox.JsonObjectRequest

class NewsViewModel() : ViewModel(){

    private val newsLiveData: MutableLiveData<MutableList<News>> = MutableLiveData()

    init {
        newsLiveData.value = createNewsList()
    }

    fun getNews() : LiveData<MutableList<News>> {
        return newsLiveData
    }

    private fun createNewsList(): MutableList<News>? {

        var newsArray : ArrayList<News>? = null

        val newsApiUrl = ""
            //"https://newsapi.org/v2/top-headlines?sources=bbc-news&apiKey=c8f383f6f6294d4ea43cb647dd77e664"

        // Request a json response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, newsApiUrl, null,
            { response ->
                // request successfully
                newsArray = ArrayList()
                val newsJsonArray = response.getJSONArray("articles")

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
                    newsArray!!.add(newsData)
                }

//                val imageView = findViewById<>().memeIv
//                Glide.with(this)
//                    .load(urlJson)
//                    .into(imageView)
            },
            {
                // request failed
               // Log.d("failed request", it.localizedMessage)
            })

        // Add the request to the RequestQueue.
        //NewsSingleton.getInstance(context).addToRequestQueue(jsonObjectRequest)

        return newsArray
    }
}