package com.example.instagram.ui.filterphoto

import com.example.instagram.data.prefs.PreferencesHelper
import com.example.instagram.model.Feed
import com.example.instagram.model.Posting
import com.example.instagram.model.User
import com.example.instagram.ui.base.BasePresenter
import com.example.instagram.utils.Base64Utils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class FilterPhotoPresenter<V: FilterPhotoContract.View, I: FilterPhotoContract.Interactor>
    @Inject constructor(var interactorPhoto: I, var pref: PreferencesHelper):
    BasePresenter<V, I>(interactorPhoto), FilterPhotoContract.Presenter<V, I> {

    override fun savedPhoto(photo: ByteArray?, identity: String) {
        getMvpView().showLoading()
        interactorPhoto.savedPhoto(photo, identity).addOnFailureListener{
            getMvpView().showMessage("Erro ao atualizado o usuário!")
            getMvpView().hideLoading()
        }.addOnSuccessListener {
            it.metadata!!.reference!!.downloadUrl!!.addOnSuccessListener {uri ->
                getMvpView().savedPosting(uri.toString())
                getMvpView().hideLoading()
            }
        }
    }


    override fun publishPhoto(publish: Posting) {
        getMvpView().showLoading()
        interactorPhoto.getUser().addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                getMvpView().onError("Erro ao salvar a postagem!")
                getMvpView().hideLoading()
            }

            override fun onDataChange(p0: DataSnapshot) {
                var user = p0.getValue(User::class.java)
                user?.qtPosting  = user?.qtPosting!! + 1

                publish.idUser = Base64Utils.encode(user?.email)
                interactor.publishPhoto(publish)
                interactorPhoto.updateUser(user)

                sendFeedAllFriends(publish, user)

                getMvpView().showMessage("Postagem feita com sucesso!")
                getMvpView().onFinish()
            }
        })
    }

    private fun sendFeedAllFriends(posting: Posting, userPosting: User){
        interactorPhoto.getAllFollowing(Base64Utils.encode(pref.getEmailUser()))!!
            .addListenerForSingleValueEvent(object: ValueEventListener{

            override fun onDataChange(p0: DataSnapshot) {
                p0.children.forEach{
                    var user = it.child("user").getValue(User::class.java!!)
                    var feed = Feed(
                        posting.pathPhoto,
                        posting.description,
                        posting.id,
                        userPosting?.nameUser!!,
                        userPosting?.photo!!
                    )
                    interactorPhoto.savedFeed(feed, user!!)
                }
                getMvpView().hideLoading()
            }

            override fun onCancelled(p0: DatabaseError) {
                getMvpView().showMessage("Erro ao enviar o feed, para seus usuários!")
            }
        })
    }
}