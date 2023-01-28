package com.example.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NewsListAdapter(private val context: Context, private val newsItems: List<String>): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(newsItem: String) {
            val itemView = itemView.findViewById<TextView>(R.id.title)
            itemView.text = newsItem

            itemView.setOnClickListener {
                Toast.makeText(context,"Clicked item: $newsItem",Toast.LENGTH_LONG).show()
            }
        }
    }

    /* Eta item_news layout access kore newsViewHolder ke dive*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsItems.size
    }

    /* Eta newsItemsList er position er value gula dive list thake*/
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsItem = newsItems[position]
        holder.bind(newsItem)
    }
}