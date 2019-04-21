package com.silasferreira.youtube.data.network.model

import com.google.gson.annotations.SerializedName
import com.silasferreira.youtube.model.Items
import com.silasferreira.youtube.model.PageInfo

data class VideoResponse (
 @SerializedName("kind") val kind : String,
 @SerializedName("etag") val etag : String,
 @SerializedName("nextPageToken") val nextPageToken : String,
 @SerializedName("regionCode") val regionCode : String,
 @SerializedName("pageInfo") val pageInfo : PageInfo,
 @SerializedName("items") val items : ArrayList<Items>
)