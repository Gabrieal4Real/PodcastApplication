package com.gabrieal.podcastapplication.di.module

import com.gabrieal.podcastapplication.network.http.BaseHttpClient
import com.gabrieal.podcastapplication.network.http.HttpClient
import com.gabrieal.podcastapplication.repository.PodcastRepository
import com.gabrieal.podcastapplication.repository.PodcastRepositoryImpl
import com.gabrieal.podcastapplication.viewmodel.podcast.PodcastViewModelImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<HttpClient> { BaseHttpClient() }
    single<PodcastRepository> { PodcastRepositoryImpl(get()) }
    viewModel { PodcastViewModelImpl(get()) }
}
