package com.silasferreira.youtube.data.network.api

import com.silasferreira.youtube.data.network.model.VideoResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    fun getVideo(
                @Query("part") part: String,
                @Query("order") order: String,
                @Query("maxResult") maxResult: String,
                @Query("key") key: String,
                @Query("channelId") channelId: String,
                @Query("q") q: String
    ) : Deferred<Response<VideoResponse>>
}