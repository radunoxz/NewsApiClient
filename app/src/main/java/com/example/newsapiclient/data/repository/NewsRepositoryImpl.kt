package com.example.newsapiclient.data.repository

import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.model.Article
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.data.util.toResource
import com.example.newsapiclient.domain.datasource.NewsRemoteDataSource
import com.example.newsapiclient.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsRemoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override suspend fun getNewsHeadlines(country: String, page: Int): Resource<ApiResponse> =
        newsRemoteDataSource.getTopHeadlines(country, page).toResource()

    override suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): Resource<ApiResponse> =
        newsRemoteDataSource.getSearchedTopHeadlines(country, page, searchQuery).toResource()

    override suspend fun saveNews(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteNews(article: Article) {
        TODO("Not yet implemented")
    }

    override fun getSavedNews(): Flow<List<Article>> {
        TODO("Not yet implemented")
    }
}
