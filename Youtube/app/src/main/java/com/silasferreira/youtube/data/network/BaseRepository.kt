package com.silasferreira.youtube.data.network

import android.util.Log
import retrofit2.Response
import java.io.IOException

open class BaseRepository{

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String): T? {

        val result : Result<T> = safeApiResult(call,errorMessage)
        var data : T? = null

        when(result) {
            is Result.Success<T> -> {
                data = result.data
            }
            is Result.Error -> {
                Log.d("1.DataRepository", "$errorMessage & Exception - ${result.exception}")
            }
        }

        return data

    }

    private suspend fun <T: Any> safeApiResult(call: suspend ()-> Response<T>, errorMessage: String) : Result<T> {
        val response = call.invoke()
        val body = response.body()
        return when {
            !response.isSuccessful -> Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
            body == null -> Result.Error(IOException("NOT FOUND"))
            else -> Result.Success(body)
        }
    }
}