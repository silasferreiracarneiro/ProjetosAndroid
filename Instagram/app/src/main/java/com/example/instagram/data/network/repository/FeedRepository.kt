package com.example.instagram.data.network.repository

import com.example.instagram.model.Comment
import com.example.instagram.model.Feed
import com.example.instagram.model.User
import com.google.firebase.database.DatabaseReference


interface FeedRepository {
    fun savedFeed(feed: Feed, user: User)
    fun gelAllFeed(): DatabaseReference
    fun updateFeed(item: Feed)
    fun savedComment(comment: Comment)
    fun getAllComment(idPosting: String): DatabaseReference
}