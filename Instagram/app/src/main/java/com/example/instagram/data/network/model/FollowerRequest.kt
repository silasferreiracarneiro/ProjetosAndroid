package com.example.instagram.data.network.model

import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.repository.FollowerRepository
import com.example.instagram.model.Follower
import com.example.instagram.utils.AppConstants.Companion.SEGUIDORES
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DatabaseReference

class FollowerRequest(var firebaseConfig: ConfigFirebaseContract): FollowerRepository {

    override fun savedFollower(follower: Follower) {
        var idFollower = follower.idFollower
        var idFollowing = follower.idFollowing
        firebaseConfig.database().child(SEGUIDORES).child(idFollowing).child(idFollower).setValue(follower)
    }

    override fun following(email: String): DatabaseReference? {
        return firebaseConfig.database().child(SEGUIDORES).child(Base64Utils.encode(email)).child(Base64Utils.encode(firebaseConfig.authFirebase().currentUser?.email))
    }
}