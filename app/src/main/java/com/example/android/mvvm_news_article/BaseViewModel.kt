package com.example.android.mvvm_news_article

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.android.mvvm_news_article.injection.component.DaggerViewModelInjector
import com.example.android.mvvm_news_article.injection.component.ViewModelInjector
import com.example.android.mvvm_news_article.injection.module.NetworkModule
import com.example.android.mvvm_news_article.ui.NewsListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is NewsListViewModel -> injector.inject(this)
        }
    }
}