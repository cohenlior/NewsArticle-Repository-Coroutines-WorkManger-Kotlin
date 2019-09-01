package com.example.android.mvvm_news_article_repository.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.mvvm_news_article_repository.R
import com.example.android.mvvm_news_article_repository.model.Article
import com.example.android.mvvm_news_article_repository.ui.NewsListAdapter

@BindingAdapter("listData")
fun bindRecyclerViewFavorites(recyclerView: RecyclerView, data: List<Article>?) {
    val adapter = recyclerView.adapter as NewsListAdapter
    if (data != null) {
        adapter.articles = data
    }
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_panorama_grey_24dp)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)

    }
}

@BindingAdapter("articleDateFormatted")
fun TextView.setArticleDateFormatted(item: Article?) {
    item?.let {
        text = formatDate(item.publishedAt)
    }
}

@BindingAdapter("goneIfNotNull")
fun goneIfNotNull(view: View, it: Any?) {
    view.visibility = if (it != null) View.GONE else View.VISIBLE
}
