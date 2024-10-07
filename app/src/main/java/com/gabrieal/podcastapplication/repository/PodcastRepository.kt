package com.gabrieal.podcastapplication.repository

import androidx.lifecycle.LiveData
import com.gabrieal.podcastapplication.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.network.api.Resource

interface PodcastRepository {
    fun getPodcastListFromAPI(): LiveData<Resource<PodcastListModel>>
}
