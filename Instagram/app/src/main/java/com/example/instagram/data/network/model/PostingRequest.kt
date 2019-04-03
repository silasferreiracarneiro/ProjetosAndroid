package com.example.instagram.data.network.model

import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.repository.PostingRepository
import com.example.instagram.model.Posting
import com.example.instagram.utils.AppConstants.Companion.POSTING
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.UploadTask

class PostingRequest(var firebaseConfig: ConfigFirebaseContract): PostingRepository {

    override fun getAllPosting(identify: String): DatabaseReference {
        return firebaseConfig.database().child(POSTING).child(identify)
    }

    override fun savedPhoto(photo: ByteArray?, identity: String): UploadTask {
        return firebaseConfig.storage().reference
            .child("images/posting/user/$identity.jpg").putBytes(photo!!)
    }

    override fun publishPhoto(publish: Posting) {
        firebaseConfig.database().child(POSTING).child(publish.idUser).child(publish.id).setValue(publish)
    }
}