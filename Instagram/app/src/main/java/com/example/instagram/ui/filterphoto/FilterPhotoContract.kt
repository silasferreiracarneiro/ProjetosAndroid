package com.example.instagram.ui.filterphoto

import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.UploadTask
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface FilterPhotoContract {

    interface View: MvpView {
        fun savedPosting(pathPhoto: String)
    }

    interface Presenter<V: FilterPhotoContract.View, I: FilterPhotoContract.Interactor>: MvpPresenter<V, I>{
        fun publishPhoto(publish: Posting)
        fun savedPhoto(photo: ByteArray?, identity: String)
    }

    interface Interactor: MvpInteractor{
        fun publishPhoto(publish: Posting)
        fun savedPhoto(photo: ByteArray?, identity: String): UploadTask
        fun getUser(): DatabaseReference
        fun updateUser(user: User)
        fun getAllFollowing(idUser: String): DatabaseReference?
    }
}