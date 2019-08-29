package com.example.android.mvvm_news_article.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mvvm_news_article.BR
import com.example.android.mvvm_news_article.databinding.ListItemArticleBinding
import com.example.android.mvvm_news_article.databinding.ListItemHeaderBinding
import com.example.android.mvvm_news_article.model.Article
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val ITEM_VIEW_TYPE_HEADER = 0
private const val ITEM_VIEW_TYPE_ITEM = 1

class NewsListAdapter(val viewModel: NewsListViewModel) :
    ListAdapter<DataItem, RecyclerView.ViewHolder>(DiffCallback()) {

    private val adapterScope = CoroutineScope(Dispatchers.Default)

    fun addHeaderAndSubmitList(list: List<Article>?) {
        adapterScope.launch {
            val items = when (list) {
                null -> listOf(DataItem.Header)
                else -> listOf(DataItem.Header) + list.map { DataItem.ArticleItem(it) }
            }
            withContext(Dispatchers.Main) {
                submitList(items)
            }
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM -> ViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                val articleItem = getItem(position) as DataItem.ArticleItem
                holder.bind(articleItem.article)
            }
            is TextViewHolder -> {
                holder.bind(viewModel)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is DataItem.Header -> ITEM_VIEW_TYPE_HEADER
            is DataItem.ArticleItem -> ITEM_VIEW_TYPE_ITEM
        }
    }

    class TextViewHolder(val bindingHeader: ListItemHeaderBinding) :
        RecyclerView.ViewHolder(bindingHeader.root) {

        fun bind(viewModel: NewsListViewModel) {
            bindingHeader.viewModel = viewModel
            bindingHeader.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): TextViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ListItemHeaderBinding.inflate(layoutInflater, parent, false)
                return TextViewHolder(view)
            }
        }
    }

    class ViewHolder private constructor(val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Article) {
            binding.article = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = ListItemArticleBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(view)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<DataItem>() {
        override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem.id == newItem.id
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
            return oldItem == newItem
        }
    }
}

sealed class DataItem {
    abstract val id: Long

    data class ArticleItem(val article: Article) : DataItem() {
        override val id = article.id
    }

    object Header : DataItem() {
        override val id = Long.MIN_VALUE
    }
}