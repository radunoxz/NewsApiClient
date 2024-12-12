package com.example.newsapiclient.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import com.example.newsapiclient.domain.usecase.GetSearchedNewsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : ViewModel() {
    private val _newsHeadlines = MutableLiveData<Resource<ApiResponse>>()
    val newsHeadlines: LiveData<Resource<ApiResponse>>
        get() = _newsHeadlines

    private val _searchedNews = MutableLiveData<Resource<ApiResponse>>()
    val searchedNews: LiveData<Resource<ApiResponse>>
        get() = _searchedNews

    fun getNewsHeadlines(country: String, page: Int) {
        viewModelScope.launch {
            _newsHeadlines.value = Resource.Loading()
            val response = getHeadlines(country, page)
            if (response == null) {
                _newsHeadlines.value = Resource.Error("Something went wrong")
            } else {
                _newsHeadlines.value = Resource.Success(response)
            }
        }
    }

    fun getSearchedHeadlineNews(country: String, page: Int, searchQuery: String) {
        viewModelScope.launch {
            _newsHeadlines.value = Resource.Loading()

            val response = getSearchedNews(country, page, searchQuery)
            if (response == null) {
                _newsHeadlines.value = Resource.Error("Something went wrong")
            } else {
                _newsHeadlines.value = Resource.Success(response)
            }
        }
    }

    private suspend fun getHeadlines(country: String, page: Int): ApiResponse? =
        withContext(Dispatchers.IO) {
            val response = try {
                getNewsHeadlinesUseCase.getNewsHeadlines(country, page)
            } catch (e: Exception) {
                null
            }
            response?.transform()
        }

    private suspend fun getSearchedNews(
        country: String,
        page: Int,
        searchQuery: String
    ): ApiResponse? =
        withContext(Dispatchers.IO) {
            val response = try {
                getSearchedNewsUseCase.getSearchedNews(country, page, searchQuery)
            } catch (e: Exception) {
                null
            }
            response?.transform()
        }

    private fun Resource<ApiResponse>.transform(): ApiResponse = ApiResponse(
        status = data?.status ?: throw Exception("status is null"),
        totalResults = data?.totalResults ?: throw Exception("totalResults is null"),
        articles = data?.articles ?: throw Exception("articles is null")
    )
}
