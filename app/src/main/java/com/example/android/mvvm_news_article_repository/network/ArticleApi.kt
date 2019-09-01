package com.example.android.mvvm_news_article_repository.network

import com.example.android.mvvm_news_article_repository.model.ArticleResult
import com.example.android.mvvm_news_article_repository.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

interface ArticleApi{
    @Headers("X-Api-Key: 30db667d79ad45ab8b380ffa7f11d51c")
    @GET("v2/top-headlines?country=us")
    fun getArticles(): Deferred<ArticleResult>
}

object NetworkService{
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val articles: ArticleApi = retrofit.create(ArticleApi::class.java)
}