package com.example.instagram.model

import com.google.firebase.database.Exclude

class Follower(
    @Exclude var idFollower: String = "",
    @Exclude var idFollowing: String = "",
    var user: User = User()
) {
}