package com.example.android.mvvm_news_article.injection.component

import com.example.android.mvvm_news_article.injection.module.NetworkModule
import com.example.android.mvvm_news_article.ui.NewsListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for the viewModels.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified NewsListViewModel.
     * @param NewsListViewModel NewsListViewModel in which to inject the dependencies
     */
    fun inject(postListViewModel: NewsListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}