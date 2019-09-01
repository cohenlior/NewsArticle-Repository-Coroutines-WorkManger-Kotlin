package com.example.android.mvvm_news_article_repository.ui

import android.annotation.SuppressLint
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mvvm_news_article_repository.databinding.ListItemArticleBinding
import com.example.android.mvvm_news_article_repository.model.Article

class NewsListAdapter : RecyclerView.Adapter<ViewHolder>() {

    var articles: List<Article> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = articles.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val articleItem = articles[position]
        holder.bind(articleItem)
    }
}

class ViewHolder private constructor(val binding: ListItemArticleBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Article) {
        binding.article = item
        binding.executePendingBindings()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = ListItemArticleBinding.inflate(layoutInflater, parent, false)
            return ViewHolder(view)
        }
    }
}