package com.example.android.mvvm_news_article_repository.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg articles: Article)

    @Query("DELETE FROM article")
    fun clear()
}