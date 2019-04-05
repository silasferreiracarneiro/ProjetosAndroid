package com.example.instagram.data.network.model

import com.example.instagram.data.firebase.ConfigFirebaseContract
import com.example.instagram.data.network.repository.FeedRepository
import com.example.instagram.model.Comment
import com.example.instagram.model.Feed
import com.example.instagram.model.User
import com.example.instagram.utils.AppConstants.Companion.COMMENTS
import com.example.instagram.utils.AppConstants.Companion.FEED
import com.example.instagram.utils.AppConstants.Companion.LIKES
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DatabaseReference

class FeedRequest(var firebaseConfig: ConfigFirebaseContract): FeedRepository {

    override fun getAllComment(idPosting: String): DatabaseReference {
        return firebaseConfig.database().child(COMMENTS).child(idPosting)
    }

    override fun savedComment(comment: Comment) {
        var ref = firebaseConfig.database().child(COMMENTS).child(comment.idPosting)
        comment.idComment = ref.push().key!!
        ref.child(comment.idComment).setValue(comment)
    }

    override fun gelAllFeed(): DatabaseReference {
        return firebaseConfig.database().child(FEED).child(Base64Utils.encode(firebaseConfig?.authFirebase()?.currentUser?.email!!))
    }

    override fun savedFeed(feed: Feed, user: User) {
        firebaseConfig.database().child(FEED).child(Base64Utils.encode(user.email)).child(feed.id).setValue(feed)
    }

    override fun updateFeed(item: Feed) {
        firebaseConfig.database().child(FEED).child(Base64Utils.encode(firebaseConfig?.authFirebase()?.currentUser?.email!!))
            .child(item.id).child(LIKES).setValue(item.likes)
    }
}
