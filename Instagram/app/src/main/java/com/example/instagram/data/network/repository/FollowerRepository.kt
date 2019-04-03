package com.example.instagram.data.network.repository

import com.example.instagram.model.Follower
import com.google.firebase.database.DatabaseReference

interface FollowerRepository {

    fun savedFollower(follower: Follower)
    fun savedFolloweres(follower: Follower)
    fun following(email: String): DatabaseReference?
    fun getAllFollowing(idUser: String): DatabaseReference?
}