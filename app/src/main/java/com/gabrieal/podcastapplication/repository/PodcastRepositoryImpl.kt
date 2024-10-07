package com.gabrieal.podcastapplication.repository

import androidx.lifecycle.LiveData
import com.gabrieal.podcastapplication.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.network.api.NetworkCall
import com.gabrieal.podcastapplication.network.api.NewAPIService
import com.gabrieal.podcastapplication.network.api.Resource
import com.gabrieal.podcastapplication.network.http.HttpClient

class PodcastRepositoryImpl(private val httpClient: HttpClient) : PodcastRepository {
    private val podcastListCall = NetworkCall<PodcastListModel>()
    private fun getApiService(): NewAPIService {
        return httpClient.getNewApiService()
    }

    override fun getPodcastListFromAPI(): LiveData<Resource<PodcastListModel>> {
        return podcastListCall.makeCall(getApiService().getPodcastList())
    }
}
