package com.gabrieal.podcastapplication.network.api

import com.gabrieal.podcastapplication.models.error.Error

class ResourceError {
    var errors: ArrayList<Error> = ArrayList()
    var error_description: String? = null

    override fun toString(): String {
        if (errors.isEmpty()) {
            return if (error_description.isNullOrEmpty().not()) error_description!! else ""
        }
        var errorText = ""
        for (e in errors) {
            errorText = if (errorText.isEmpty()) e.message else errorText + ", " + e.message
        }
        return errorText
    }
}