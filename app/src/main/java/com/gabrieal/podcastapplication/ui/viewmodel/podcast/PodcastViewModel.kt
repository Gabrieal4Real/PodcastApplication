package com.gabrieal.podcastapplication.ui.viewmodel.podcast

import androidx.lifecycle.LiveData
import com.gabrieal.podcastapplication.data.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.data.api.ResourceError

interface PodcastViewModel {
    fun getPodcastList()
    fun observeLoading(): LiveData<Boolean>
    fun observeError(): LiveData<ResourceError?>

    fun observePodcastList(): LiveData<PodcastListModel?>

    fun selectedPodcast(selectedMp3: String)
}