package com.example.instagram.ui.comment

import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.model.Comment
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class CommentPresenter<V: CommentContract.View, I: CommentContract.Interactor>
    @Inject constructor(var feedIterator: I, var pref: PreferencesHelper):
    BasePresenter<V, I>(feedIterator), CommentContract.Presenter<V, I> {


    override fun savedComment(comment: Comment) {

        if(comment.text == "" || comment.text.isEmpty()){
            getMvpView().onError("Informe um comentário!")
        }else{
            feedIterator.getuser(Base64Utils.encode(pref.getEmailUser())).addValueEventListener(object: ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    getMvpView().onError("Erro ao buscar o usuário logado!")
                }

                override fun onDataChange(p0: DataSnapshot) {
                    var user = p0.getValue(User::class.java)
                    comment.nameUser = user?.nameUser!!
                    comment.idUser = Base64Utils.encode(user?.email)
                    comment.pathPhotoUser = user?.photo
                }
            })
        }
    }
}
