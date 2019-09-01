package com.example.android.mvvm_news_article_repository.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.android.mvvm_news_article_repository.databinding.ActivityArticleListBinding
import com.google.android.material.snackbar.Snackbar

class NewsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleListBinding
    private lateinit var viewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            com.example.android.mvvm_news_article_repository.R.layout.activity_article_list
        )

        val application = requireNotNull(this).application
        viewModel = ViewModelProviders.of(this, ArticleViewModelFactory(application))
            .get(NewsListViewModel::class.java)

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        binding.articleList.adapter = NewsListAdapter()
    }
}
