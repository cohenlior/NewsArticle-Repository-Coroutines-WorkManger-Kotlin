package com.example.android.mvvm_news_article.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.android.mvvm_news_article.model.Article

@Dao
interface ArticleDao {
    @get:Query("SELECT * FROM article")
    val all: List<Article>

    @Insert
    fun insertAll(vararg articles: Article)

    @Query("SELECT * FROM article ORDER BY id DESC LIMIT 1")
    fun getArticle(): Article?

    @Query("DELETE FROM article")
    fun clear()
}