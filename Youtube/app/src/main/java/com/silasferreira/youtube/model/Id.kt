package com.silasferreira.youtube.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Id (

	@SerializedName("kind") val kind : String,
	@SerializedName("videoId") val videoId : String
) : Serializable