package com.example.instagram.ui.filterphoto

import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class FilterPhotoPresenter<V: FilterPhotoContract.View, I: FilterPhotoContract.Interactor>
    @Inject constructor(var interactorPhoto: I):
    BasePresenter<V, I>(interactorPhoto), FilterPhotoContract.Presenter<V, I> {

    override fun savedPhoto(photo: ByteArray?, identity: String) {
        interactorPhoto.savedPhoto(photo, identity).addOnFailureListener{
            getMvpView().showMessage("Erro ao atualizado o usuário!")
        }.addOnSuccessListener {
            it.metadata!!.reference!!.downloadUrl!!.addOnSuccessListener {uri ->
                getMvpView().savedPosting(uri.toString())
            }
        }
    }


    override fun publishPhoto(publish: Posting) {
        interactorPhoto.getUser().addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao salvar a postagem!")
            }

            override fun onDataChange(p0: DataSnapshot) {
                var user = p0.getValue(User::class.java)
                user?.qtPosting  = user?.qtPosting!! + 1

                publish.idUser = Base64Utils.encode(user?.email)
                interactor.publishPhoto(publish)
                interactorPhoto.updateUser(user)
                getMvpView().showMessage("Postagem feita com sucesso!")
                getMvpView().onFinish()
            }
        })
    }

    private fun updateUser(identity: String){

    }
}