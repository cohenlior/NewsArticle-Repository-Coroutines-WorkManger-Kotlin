package com.example.android.mvvm_news_article.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.android.mvvm_news_article.databinding.ActivityArticleListBinding
import com.example.android.mvvm_news_article.injection.ViewModelFactory
import com.google.android.material.snackbar.Snackbar

class NewsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityArticleListBinding
    private lateinit var viewModel: NewsListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, com.example.android.mvvm_news_article.R.layout.activity_article_list)

        val application = requireNotNull(this).application
        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(NewsListViewModel::class.java)
        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.isLoaderVisible.observe(this, Observer {
            when(it){
                true -> binding.loader.visibility = View.VISIBLE
                false -> binding.loader.visibility = View.GONE
            }
        })

        binding.swipeToRefreshLayout.setOnRefreshListener {
            viewModel.onRefresh()
        }

        viewModel.refresh.observe(this, Observer {
            binding.swipeToRefreshLayout.isRefreshing = it
        })

        viewModel.errorMassage.observe(this, Observer {
            if (null != it) {
                Snackbar.make(
                    binding.root, getString(com.example.android.mvvm_news_article.R.string.load_articles_error),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        })

        binding.articleList.adapter = NewsListAdapter(viewModel)
    }
}
