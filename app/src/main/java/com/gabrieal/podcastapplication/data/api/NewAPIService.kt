package com.gabrieal.podcastapplication.data.api

import com.gabrieal.podcastapplication.data.models.podcast.PodcastListModel
import retrofit2.Call
import retrofit2.http.GET

interface NewAPIService {
    @GET(ApiRoutes.GET_PODCAST_LIST)
    fun getPodcastList(): Call<PodcastListModel>
}
