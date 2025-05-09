package com.gabrieal.podcastapplication.data.repository

import androidx.lifecycle.LiveData
import com.gabrieal.podcastapplication.data.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.data.api.NetworkCall
import com.gabrieal.podcastapplication.data.api.NewAPIService
import com.gabrieal.podcastapplication.data.api.Resource
import com.gabrieal.podcastapplication.data.http.HttpClient

class PodcastRepositoryImpl(private val httpClient: HttpClient) : PodcastRepository {
    private val podcastListCall = NetworkCall<PodcastListModel>()
    private fun getApiService(): NewAPIService {
        return httpClient.getNewApiService()
    }

    override fun getPodcastListFromAPI(): LiveData<Resource<PodcastListModel>> {
        return podcastListCall.makeCall(getApiService().getPodcastList())
    }
}
