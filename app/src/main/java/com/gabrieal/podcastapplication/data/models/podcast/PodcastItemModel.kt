package com.gabrieal.podcastapplication.data.models.podcast

data class PodcastItemModel(
    val `data`: PodcastModel?,
    val source: String?,
    val subtitle: String?,
    val title: String?,
    val type: String
)