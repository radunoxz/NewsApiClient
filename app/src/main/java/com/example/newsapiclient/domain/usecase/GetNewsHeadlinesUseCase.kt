package com.example.newsapiclient.domain.usecase

import com.example.newsapiclient.domain.repository.NewsRepository

class GetNewsHeadlinesUseCase(
    private val newsRepository: NewsRepository
) {
}
