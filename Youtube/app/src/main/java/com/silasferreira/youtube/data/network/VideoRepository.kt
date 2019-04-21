package com.silasferreira.youtube.data.network

import com.silasferreira.youtube.data.network.model.VideoResponse

interface VideoRepository {

    suspend fun getVideo(search: String): VideoResponse?
}