package com.example.newsapiclient.presentation.news

import com.example.newsapiclient.data.model.Article

internal sealed class NewsState {
    data class Success(val news: List<Article>) : NewsState()
    data class Error(val message: String) : NewsState()
    object Loading : NewsState()
}
