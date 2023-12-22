package com.example.medictest3

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.medictest3.databinding.ActionsAndNewsLayoutBinding

class NewsAdapter2(private val context: Context) : RecyclerView.Adapter<NewsAdapter2.NewsViewHolder>() {

    class NewsViewHolder(val binding: ActionsAndNewsLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    private var news: Array<NewsClass>
    private val myApp : App = context.applicationContext as App
    init {
//        val size = minOf(myApp.newsDescriptions.size,
//            myApp.newsPrices.size,
//            myApp.newsTitles.size,
//            myApp.newsImages.length(),
//            myApp.newsBackgrounds.length())

        news = myApp.news

//        for ( i in 0..<size){
//            news.add(
//                NewsClass(
//                    myApp.newsTitles[i],
//                    myApp.newsPrices[i],
//                    myApp.newsDescriptions[i],
//                    myApp.newsImages.getDrawable(i),
//                    myApp.newsBackgrounds.getDrawable(i)
//                )
//            )
//        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActionsAndNewsLayoutBinding.inflate(inflater, parent, false)

        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val _news = news[position]
        val context = holder.itemView.context

        with(holder.binding) {
            tvNewsTitle.text = _news.title
            tvNewsDescriprion.text = _news.description
            val priceText = "${_news.price} ₽"
            tvNewsPrice.text = priceText
            ivNewsImage.setImageDrawable(_news.image)
            clNews.background=_news.bg
        }
    }

}