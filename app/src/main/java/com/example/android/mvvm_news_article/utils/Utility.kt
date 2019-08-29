package com.example.android.mvvm_news_article.utils

import android.content.res.Resources
import com.example.android.mvvm_news_article.R
import java.text.SimpleDateFormat
import java.util.*

fun convertLongToDateString(systemTime: Long, resources: Resources): String {
    return resources.getString(R.string.formatted_last_sync, SimpleDateFormat("dd-MMM', 'HH:mm", Locale.getDefault())
            .format(systemTime).toString())
}

fun formatDate(publishedAt: String?): String {
    val inputFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    val outputFormatter = SimpleDateFormat("EEE, d MMM yyyy HH:mm", Locale.getDefault())
    val date = inputFormatter.parse(publishedAt)
    return outputFormatter.format(date)
}

fun isUpdated(lastUpdateInMillis: Long?): Boolean {
    return System.currentTimeMillis() - (lastUpdateInMillis ?: System.currentTimeMillis()) < 60000

}