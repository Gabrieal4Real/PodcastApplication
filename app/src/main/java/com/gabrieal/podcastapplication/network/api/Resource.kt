package com.gabrieal.podcastapplication.network.api

import com.gabrieal.podcastapplication.network.api.ResourceError

class Resource<T> private constructor(
    val status: Status,
    val data: T?,
    val resourceError: ResourceError?
) {
    enum class Status {
        SUCCESS, ERROR, LOADING
    }

    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(resourceError: ResourceError?): Resource<T> {
            return Resource(Status.ERROR, null, resourceError)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}