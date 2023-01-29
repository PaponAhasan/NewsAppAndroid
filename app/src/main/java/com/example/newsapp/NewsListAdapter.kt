package com.example.newsapp

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val context: Context) :
    RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    private val newsItems = ArrayList<News>()

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(newsItem: News) {
            val newsTitle = itemView.findViewById<TextView>(R.id.newsTitle)
            val newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
            val newsDescription = itemView.findViewById<TextView>(R.id.newsDescription)
            val newsAuthorDate = itemView.findViewById<TextView>(R.id.newsAuthorDate)

            newsTitle.text = newsItem.title
            newsDescription.text = newsItem.description
            newsAuthorDate.text = "${newsItem.author} | ${newsItem.publishedAt}"

            Glide.with(itemView.context)
                .load(newsItem.urlToImage)
                .into(newsImage)

            itemView.setOnClickListener {
                // initializing object for custom chrome tabs
                val intentBuilder = CustomTabsIntent.Builder()

                //set the toolbar color
                val params = CustomTabColorSchemeParams.Builder()
                params.setToolbarColor(ContextCompat.getColor(itemView.context, R.color.purple_200))
                intentBuilder.setDefaultColorSchemeParams(params.build())

                // Set the custom close button icon
                val closeButtonIcon = BitmapFactory.decodeResource(context.resources, R.drawable.ic_arrow_back)
                intentBuilder.setCloseButtonIcon(closeButtonIcon)

                // shows the title of web-page in toolbar
                intentBuilder.setShowTitle(true)

                val customTabsIntent = intentBuilder.build()
                customTabsIntent.launchUrl(itemView.context, Uri.parse(newsItem.url))

            }
        }
    }

    fun notifyNewsData(updateNews: ArrayList<News>) {
        newsItems.clear()
        newsItems.addAll(updateNews)
        notifyDataSetChanged()
    }

    /* Eta item_news layout access kore newsViewHolder ke dive*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
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