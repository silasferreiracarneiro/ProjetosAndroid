package com.silasferreira.youtube.data

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.silasferreira.youtube.utils.AppContants.Companion.URL_BASE
import com.silasferreira.youtube.data.network.api.ApiService
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class RetrofitConfig {

    fun getIntanceRetrofit() = Retrofit.Builder()
        .baseUrl(URL_BASE)
        .client(OkHttpClient().newBuilder().build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val api: ApiService = getIntanceRetrofit().create(ApiService::class.java)
}