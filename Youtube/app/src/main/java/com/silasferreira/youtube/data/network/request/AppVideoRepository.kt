package com.silasferreira.youtube.data.network.request

import com.silasferreira.youtube.utils.AppContants.Companion.COVER_ID
import com.silasferreira.youtube.utils.AppContants.Companion.DATE
import com.silasferreira.youtube.utils.AppContants.Companion.GOOGLE_API_KEY
import com.silasferreira.youtube.utils.AppContants.Companion.MAX_RESULT
import com.silasferreira.youtube.utils.AppContants.Companion.SNIPPET
import com.silasferreira.youtube.data.RetrofitConfig
import com.silasferreira.youtube.data.network.BaseRepository
import com.silasferreira.youtube.data.network.VideoRepository
import com.silasferreira.youtube.data.network.model.VideoResponse

class AppVideoRepository : BaseRepository() ,VideoRepository {

    private var service: RetrofitConfig = RetrofitConfig()

    override suspend fun getVideo(search: String): VideoResponse? {
        return safeApiCall(
            call = { service.api.getVideo(SNIPPET, DATE, MAX_RESULT, GOOGLE_API_KEY, COVER_ID, search).await() },
            errorMessage = "Erro ao buscar os vídeos!"
        )
    }
}