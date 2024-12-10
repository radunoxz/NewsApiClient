package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {
    suspend fun getNewsHeadlines(): Resource<ApiResponse> = newsRepository.getNewsHeadlines()
}
