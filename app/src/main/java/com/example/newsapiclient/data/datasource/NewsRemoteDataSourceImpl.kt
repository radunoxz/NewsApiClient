package com.example.newsapiclient.data.datasource

import com.example.newsapiclient.data.api.NewsApiService
import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.domain.datasource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsApiService: NewsApiService
) : NewsRemoteDataSource {
    override suspend fun getTopHeadlines(country: String, page: Int): Response<ApiResponse> =
        newsApiService.getTopHeadlines(country, page)

    override suspend fun getSearchedTopHeadlines(
        country: String,
        page: Int,
        searchQuery: String
    ): Response<ApiResponse> =
        newsApiService.getSearchedTopHeadlines(country, page, searchQuery)

}
