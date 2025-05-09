package com.gabrieal.podcastapplication.data.repository

import androidx.lifecycle.LiveData
import com.gabrieal.podcastapplication.data.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.data.api.Resource

interface PodcastRepository {
    fun getPodcastListFromAPI(): LiveData<Resource<PodcastListModel>>
}
