package com.example.instagram.model

import java.io.Serializable

class User(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var nameUser: String = "",
    var photo: String = "",
    var qtFollower: Int = 0,
    var qtFollowing: Int = 0,
    var qtPosting: Int = 0
) : Serializable{
}