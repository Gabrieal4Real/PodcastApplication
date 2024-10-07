package com.gabrieal.podcastapplication.viewmodel.podcast

import androidx.lifecycle.LiveData
import com.gabrieal.podcastapplication.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.network.api.ResourceError

interface PodcastViewModel {
    fun getPodcastList()
    fun observeLoading(): LiveData<Boolean>
    fun observeError(): LiveData<ResourceError?>

    fun observePodcastList(): LiveData<PodcastListModel?>

}