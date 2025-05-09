package com.gabrieal.podcastapplication.di.module

import com.gabrieal.podcastapplication.data.http.BaseHttpClient
import com.gabrieal.podcastapplication.data.http.HttpClient
import com.gabrieal.podcastapplication.data.repository.PodcastRepository
import com.gabrieal.podcastapplication.data.repository.PodcastRepositoryImpl
import com.gabrieal.podcastapplication.ui.viewmodel.podcast.PodcastViewModelImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    singleOf(::BaseHttpClient) { bind<HttpClient>() }
    singleOf(::PodcastRepositoryImpl) { bind<PodcastRepository>() }
    singleOf(::PodcastViewModelImpl)
}
