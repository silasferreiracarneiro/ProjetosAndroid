package com.example.instagram.data.network.repository

import com.example.instagram.model.Posting
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.UploadTask

interface PostingRepository {

    fun savedPhoto(photo: ByteArray?, identity: String): UploadTask
    fun publishPhoto(publish: Posting)
    fun getAllPosting(identify: String): DatabaseReference
}