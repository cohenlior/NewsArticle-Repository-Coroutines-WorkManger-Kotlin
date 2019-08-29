package com.example.android.mvvm_news_article.network

import com.example.android.mvvm_news_article.model.ArticleResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface ArticleApi{
    @Headers("X-Api-Key: 30db667d79ad45ab8b380ffa7f11d51c")
    @GET("v2/top-headlines?country=us")
    fun getArticles(): Observable<ArticleResult>
}