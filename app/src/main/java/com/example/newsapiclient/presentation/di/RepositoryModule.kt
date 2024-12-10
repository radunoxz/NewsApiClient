package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.data.repository.NewsRepositoryImpl
import com.example.newsapiclient.domain.datasource.NewsRemoteDataSource
import com.example.newsapiclient.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun providesNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository = NewsRepositoryImpl(
        newsRemoteDataSource
    )
}
