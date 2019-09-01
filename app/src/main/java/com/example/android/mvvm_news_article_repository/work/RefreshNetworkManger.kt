package com.example.android.mvvm_news_article_repository.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.android.mvvm_news_article_repository.model.database.getDatabase
import com.example.android.mvvm_news_article_repository.repository.ArticlesRepository
import retrofit2.HttpException

class RefreshNetworkManger(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshNetworkManger"
    }

    override suspend fun doWork(): Payload {
        val database = getDatabase(applicationContext)
        val repository = ArticlesRepository(database)

        return try {
            repository.refreshArticles()
            Payload(Result.SUCCESS)
        } catch (e: HttpException) {
            Payload(Result.RETRY)
        }
    }
}