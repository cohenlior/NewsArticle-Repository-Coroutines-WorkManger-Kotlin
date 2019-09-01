package com.example.android.mvvm_news_article_repository.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.android.mvvm_news_article_repository.model.Article
import com.example.android.mvvm_news_article_repository.model.ArticleDao

@Database(entities = [Article::class], version = 1,  exportSchema = false)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}

private lateinit var INSTANCE: ArticleDatabase

fun getDatabase(context: Context): ArticleDatabase {
    synchronized(ArticleDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                ArticleDatabase::class.java,
                "articles").build()
        }
    }
    return INSTANCE
}