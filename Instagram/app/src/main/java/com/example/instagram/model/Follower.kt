package com.example.instagram.model

import com.google.firebase.database.Exclude

class Follower(
    @get:Exclude var idFollower: String = "",
    @get:Exclude var idFollowing: String = "",
    var user: User = User()
) {
}