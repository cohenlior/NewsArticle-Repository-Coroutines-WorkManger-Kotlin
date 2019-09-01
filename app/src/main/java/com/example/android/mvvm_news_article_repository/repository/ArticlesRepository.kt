package com.example.android.mvvm_news_article_repository.repository

import androidx.lifecycle.LiveData
import com.example.android.mvvm_news_article_repository.model.Article
import com.example.android.mvvm_news_article_repository.model.database.ArticleDatabase
import com.example.android.mvvm_news_article_repository.network.NetworkService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticlesRepository(private val database: ArticleDatabase) {
    /**
     * Fetch the articles from the web
     **/
    suspend fun refreshArticles() {
        withContext(Dispatchers.IO) {
            val result = NetworkService.articles.getArticles().await()
            database.articleDao().insertAll(*result.articles.toTypedArray())
        }

    }

    /**
     * An article list shown on screen
     **/
    val articles = database.articleDao().getAllArticles()


}