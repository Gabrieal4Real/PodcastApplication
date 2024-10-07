package com.gabrieal.podcastapplication.viewmodel.podcast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.gabrieal.podcastapplication.models.podcast.PodcastListModel
import com.gabrieal.podcastapplication.network.api.Resource
import com.gabrieal.podcastapplication.network.api.Resource.Status.ERROR
import com.gabrieal.podcastapplication.network.api.Resource.Status.LOADING
import com.gabrieal.podcastapplication.network.api.Resource.Status.SUCCESS
import com.gabrieal.podcastapplication.network.api.ResourceError
import com.gabrieal.podcastapplication.repository.PodcastRepository

class PodcastViewModelImpl(private val podcastRepository: PodcastRepository) : ViewModel(),
    PodcastViewModel {
    private val podcastListLiveData = MutableLiveData<PodcastListModel?>()
    private val isLoading = MutableLiveData<Boolean>()
    private val isError = MutableLiveData<ResourceError?>()

    private val fetchPodcastListObserver: Observer<Resource<PodcastListModel>> = Observer { t ->
        processPodcastListResponse(t)
    }

    override fun getPodcastList() {
        podcastRepository.getPodcastListFromAPI()
            .observeForever { fetchPodcastListObserver.onChanged(it) }
    }

    override fun observeLoading(): LiveData<Boolean> {
        return isLoading
    }

    override fun observeError(): LiveData<ResourceError?> {
        return isError
    }

    private fun processPodcastListResponse(response: Resource<PodcastListModel>?) {
        when (response?.status) {
            LOADING -> {
                isLoading.value = true
            }
            SUCCESS -> {
                isLoading.value = false
                podcastListLiveData.value = response.data
            }
            ERROR  -> {
                isLoading.value = false
                isError.value = response.resourceError
            }
            else -> {}
        }
    }

    override fun observePodcastList(): LiveData<PodcastListModel?> {
        return podcastListLiveData
    }
}