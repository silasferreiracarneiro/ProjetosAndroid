package com.example.instagram.data.network.model

import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.repository.FollowerRepository
import com.example.instagram.model.Follower
import com.example.instagram.utils.AppConstants.Companion.SEGUIDORES
import com.example.instagram.utils.AppConstants.Companion.SEGUINDO
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DatabaseReference

class FollowerRequest(var firebaseConfig: ConfigFirebaseContract): FollowerRepository {

    override fun savedFolloweres(follower: Follower) {
        var idFollower = follower.idFollower
        var idFollowing = follower.idFollowing
        firebaseConfig.database().child(SEGUINDO).child(idFollowing).child(idFollower).setValue(follower)
    }

    override fun savedFollower(follower: Follower) {
        var idFollowing = follower.idFollowing
        firebaseConfig.database().child(SEGUIDORES).child(idFollowing)
            .child(Base64Utils.encode(firebaseConfig.authFirebase().currentUser?.email)).setValue(follower)

    }

    override fun following(email: String, userLog: String): DatabaseReference? {
        return firebaseConfig.database().child(SEGUIDORES).child(Base64Utils.encode(userLog)).child(Base64Utils.encode(email))
    }

    override fun getAllFollowing(idUser: String): DatabaseReference? {
        return firebaseConfig.database().child(SEGUIDORES).child(idUser)
    }
}