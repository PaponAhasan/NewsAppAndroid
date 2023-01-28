package com.example.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val recyclerView = findViewById<RecyclerView>(R.id.newsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val newsList = createNewsList()
        recyclerView.adapter = NewsListAdapter(this, newsList)
    }

    private fun createNewsList(): List<String>{
        val newsList = mutableListOf<String>()
        for(i in 1..50){
            newsList.add("Item no $i")
        }
        return newsList
    }
}