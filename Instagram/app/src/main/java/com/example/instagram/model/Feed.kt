package com.example.instagram.model

class Feed(
    var pathPhotoPosting: String = "",
    var description: String = "",
    var id: String = "",
    var nameUser: String = "",
    var pathPhotoUser: String = "",
    var likes: ArrayList<LikePolisting> = arrayListOf()
) {
}