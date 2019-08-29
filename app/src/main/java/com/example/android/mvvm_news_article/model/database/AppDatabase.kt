package com.example.android.mvvm_news_article.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.mvvm_news_article.model.Article
import com.example.android.mvvm_news_article.model.ArticleDao

@Database(entities = [Article::class], version = 1,  exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}