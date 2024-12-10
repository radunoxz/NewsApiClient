package com.example.newsapiclient.presentation.di

import com.example.newsapiclient.data.api.NewsApiService
import com.example.newsapiclient.data.datasource.NewsRemoteDataSourceImpl
import com.example.newsapiclient.domain.datasource.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(newsApiService: NewsApiService): NewsRemoteDataSource =
        NewsRemoteDataSourceImpl(newsApiService)
}
