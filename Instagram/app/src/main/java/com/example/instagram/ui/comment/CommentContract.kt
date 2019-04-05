package com.example.instagram.ui.comment

import com.example.instagram.model.Comment
import com.google.firebase.database.DatabaseReference
import com.silasferreira.whatsapp.ui.base.MvpInteractor
import com.silasferreira.whatsapp.ui.base.MvpPresenter
import com.silasferreira.whatsapp.ui.base.MvpView

interface CommentContract {
    interface View: MvpView{
        fun setAllComment(list: ArrayList<Comment>)
    }

    interface Presenter<V: CommentContract.View, I: CommentContract.Interactor>: MvpPresenter<V, I>{
        fun savedComment(comment: Comment)
        fun getAllComment(idPosting: String)
    }

    interface Interactor: MvpInteractor{
        fun savedComment(comment: Comment)
        fun getuser(email: String): DatabaseReference
        fun getAllComment(idPosting: String): DatabaseReference
    }
}