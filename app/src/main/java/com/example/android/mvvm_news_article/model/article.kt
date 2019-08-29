package com.example.android.mvvm_news_article.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.library.baseAdapters.BR
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ArticleResult(val articles: List<Article>)

@Entity
data class Article (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val description: String?,
    val publishedAt: String?,
    val title: String?,
    val urlToImage: String?
): BaseObservable() {
    @get:Bindable
    var timestamp: Long = System.currentTimeMillis()
        get() = if(field > 0) field else {
            field = System.currentTimeMillis()
            field
        }
        set(value){
            field = value
            notifyPropertyChanged(BR.timestamp)
        }
}