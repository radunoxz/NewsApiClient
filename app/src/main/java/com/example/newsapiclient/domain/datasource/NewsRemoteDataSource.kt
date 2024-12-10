package com.example.newsapiclient.domain.datasource

import com.example.newsapiclient.data.model.ApiResponse
import retrofit2.Response

interface NewsRemoteDataSource {
    suspend fun getTopHeadlines(country: String, page: Int): Response<ApiResponse>
}
