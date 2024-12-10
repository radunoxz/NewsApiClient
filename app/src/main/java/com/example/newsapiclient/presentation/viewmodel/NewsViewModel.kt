package com.example.newsapiclient.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapiclient.data.model.ApiResponse
import com.example.newsapiclient.data.util.Resource
import com.example.newsapiclient.domain.usecase.GetNewsHeadlinesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsViewModel(
    private val getNewsHeadlinesUseCase: GetNewsHeadlinesUseCase
) : ViewModel() {
    private val newsHeadlines = MutableLiveData<Resource<ApiResponse>>()
    val _newsHeadlinesLiveData: MutableLiveData<Resource<ApiResponse>>
        get() = newsHeadlines

    fun getNewsHeadlines(country: String, page: Int) {
        viewModelScope.launch {
            newsHeadlines.postValue(Resource.Loading())

            val response = getHeadlines(country, page)
            if (response == null){
                newsHeadlines.postValue(Resource.Error("Something went wrong"))
            }else{
                newsHeadlines.postValue(Resource.Success(response))
            }
        }
    }

    private suspend fun getHeadlines(country: String, page: Int): ApiResponse? =
        withContext(Dispatchers.IO) {
           val response =  try {
               getNewsHeadlinesUseCase.getNewsHeadlines(country, page)
            } catch (e: Exception) {
                null
            }
            response?.transform()
        }

    private fun Resource<ApiResponse>.transform(): ApiResponse = ApiResponse(
        status = this.data?.status ?: throw Exception("status is null"),
        totalResults = this.data?.totalResults ?: throw Exception("totalResults is null"),
        articles = this.data?.articles ?: throw Exception("articles is null")
    )
}
