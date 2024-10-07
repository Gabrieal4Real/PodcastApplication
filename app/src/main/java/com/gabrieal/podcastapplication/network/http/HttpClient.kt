package com.gabrieal.podcastapplication.network.http

import com.gabrieal.podcastapplication.network.api.NewAPIService
import retrofit2.Retrofit

interface HttpClient {
    fun getNewApiService(): NewAPIService
    fun getNewRetrofit(): Retrofit
}