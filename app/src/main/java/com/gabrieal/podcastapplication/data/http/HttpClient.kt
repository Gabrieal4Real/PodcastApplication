package com.gabrieal.podcastapplication.data.http

import com.gabrieal.podcastapplication.data.api.NewAPIService
import retrofit2.Retrofit

interface HttpClient {
    fun getNewApiService(): NewAPIService
    fun getNewRetrofit(): Retrofit
}