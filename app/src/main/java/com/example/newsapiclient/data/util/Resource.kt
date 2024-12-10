package com.example.newsapiclient.data.util

import retrofit2.Response

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
}

fun <T> Response<T>.toResource(): Resource<T> {
    if (this.isSuccessful) {
        this.body()?.let { result ->
            return Resource.Success(result)
        }
    }
    return Resource.Error(this.message())
}
