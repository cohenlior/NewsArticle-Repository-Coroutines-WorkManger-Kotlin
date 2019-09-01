package com.example.android.mvvm_news_article_repository.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.android.mvvm_news_article_repository.model.Article
import com.example.android.mvvm_news_article_repository.model.database.getDatabase
import com.example.android.mvvm_news_article_repository.repository.ArticlesRepository
import kotlinx.coroutines.*

class NewsListViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = Job()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val database = getDatabase(application)

    private val articlesRepository = ArticlesRepository(database)


    init {
        viewModelScope.launch {
            articlesRepository.refreshArticles()
        }
    }

    val articles = articlesRepository.articles

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}