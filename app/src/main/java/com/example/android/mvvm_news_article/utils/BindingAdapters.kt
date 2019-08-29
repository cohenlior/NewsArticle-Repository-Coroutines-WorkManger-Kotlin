package com.example.android.mvvm_news_article.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.android.mvvm_news_article.R
import com.example.android.mvvm_news_article.model.Article
import com.example.android.mvvm_news_article.ui.NewsListAdapter

@BindingAdapter("listData")
fun bindRecyclerViewFavorites(recyclerView: RecyclerView, data: List<Article>?) {
    val adapter = recyclerView.adapter as NewsListAdapter
    if (data != null) {
        adapter.addHeaderAndSubmitList(data)
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

@BindingAdapter("articlesLastSync")
fun TextView.setLastSync(data: List<Article>?) {
    data?.let {
        text = convertLongToDateString(data.last().timestamp, context.resources)
    }
}